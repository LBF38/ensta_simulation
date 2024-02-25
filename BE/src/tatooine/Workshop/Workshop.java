package tatooine.Workshop;


import engine.SimEngine;
import engine.SimEntity;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import tatooine.Client.Client;
import tatooine.Events.*;
import tatooine.Workshop.InitWorkshop.Frequenting;
import tatooine.Workshop.InitWorkshop.QueueType;
import tatooine.Workshop.InitWorkshop.WorkshopType;

import java.util.*;

import static java.lang.Math.abs;

@ToRecord(name = "Workshop")
public class Workshop extends SimEntity {
    /**
     * The opening time of the workshop
     */
    private final LogicalDateTime opening;
    /**
     * The closing time of the workshop
     */
    private final LogicalDateTime closing;
    /**
     * The duration of the workshop
     * In minutes.
     */
    private final LogicalDuration duration;
    /**
     * The frequency of the failure
     * In days.
     */
    private final LogicalDuration failureFrequency;
    /**
     * The standard deviation of the failure frequency
     * In days.
     */
    private final LogicalDuration failureStandardDeviation; // TODO: see how to use this.
    /**
     * The duration of the failure recovery
     * In days.
     */
    private final LogicalDuration failureRecovery;
    private final InitWorkshop initData;
    private final Queue<Client> queue = new LinkedList<>();
    private final Queue<Client> currentClients = new LinkedList<>();
    /**
     * The efficiency of the workshop over the simulation.
     * Defined as the fraction of the duration a client spent in the workshop over the duration required by the client.
     */
    private double dailyEfficiency = 0;
    /**
     * The theoretical perfect efficiency of the workshop over the current day.
     * i.e. the number of clients that could have been served perfectly.
     */
    private long dailyPerfectEfficiency = 0;
    /**
     * The efficiency of the workshop over the current day.
     * Defined as the fraction of the duration a client spent in the workshop over the duration required by the client.
     */
    private List<Double> totalEfficiency = new ArrayList<>();
    /**
     * The theoretical perfect efficiency of the workshop over the simulation.
     * i.e. the number of clients that could have been served perfectly over the simulation.
     */
    private List<Long> totalPerfectEfficiency = new ArrayList<>();

    /**
     * The history of the clients the workshop had hosted.
     */
    private HashMap<LogicalDateTime, List<Object>> workshopHistory = new HashMap<LogicalDateTime, List<Object>>();


    public Workshop(SimEngine engine, InitWorkshop ini) {
        super(engine, ini);
        this.opening = new LogicalDateTime(ini.opening.toDateTimeFrenchFormat());
        this.closing = new LogicalDateTime(ini.closing.toDateTimeFrenchFormat());
        this.duration = LogicalDuration.ofMinutes(ini.duration);
        this.failureFrequency = LogicalDuration.ofDay(ini.failureFrequency);
        this.failureRecovery = LogicalDuration.ofDay(ini.failureRecovery);
        this.failureStandardDeviation = LogicalDuration.ofDay(ini.failureStandardDeviation);
        this.initData = ini;
        // TODO: idea - rather than having everything here, it can be encapsulated inside the getters of each property.
        // => for example, getOpening() will return a LogicalDateTime object based on the ini.opening.
    }

    @Override
    protected void init() {
        super.init();
        send(new OpenWorkshop(this.opening, this));
        // TODO: add the failure event
        // TODO: add the next client event => see how to do this.
        send(new CloseWorkshop(this.closing, this));
//        send(new WorkshopFailure(this.failureFrequency, this));
    }

    @ToRecord(name = "type")
    public WorkshopType getType() {
        return initData.type;
    }

    @ToRecord(name = "duration")
    public LogicalDuration getDuration() {
        return duration;
    }

    @ToRecord(name = "opening")
    public LogicalDateTime getOpening() {
        return opening;
    }

    @ToRecord(name = "closing")
    public LogicalDateTime getClosing() {
        return closing;
    }

    @ToRecord(name = "failure recovery")
    public LogicalDuration getFailureRecovery() {
        return failureRecovery;
    }

    @ToRecord(name = "failure frequency")
    public LogicalDuration getFailureFrequency() {
        return failureFrequency;
    }

    @ToRecord(name = "failure standard deviation")
    public LogicalDuration getFailureStandardDeviation() {
        return failureStandardDeviation;
    }

    @ToRecord(name = "capacity")
    public int getCapacity() {
        return initData.capacity;
    }

    @ToRecord(name = "efficiency")
    public int getEfficiency() {
        return initData.efficiency;
    }

    @ToRecord(name = "frequenting")
    public Frequenting getFrequenting() {
        return initData.frequenting;
    }

    @ToRecord(name = "queueCapacity")
    public int getQueueCapacity() {
        return initData.queueCapacity;
    }

    @ToRecord(name = "queueType")
    public QueueType getQueueType() {
        return initData.queueType;
    }

    @ToRecord(name = "queue")
    public Queue<Client> getQueue() {
        return queue;
    }

    @ToRecord(name = "currentQueueSize")
    public int getCurrentQueueSize() {
        return queue.size();
    }

    public boolean addClient(Client client) {
        if (canAddClient()) {
            Logger.Detail(this, "addClient", "%s added to the workshop %s queue.".formatted(client, this));
            queue.add(client);
            return true;
        }
        Logger.Detail(this, "addClient", "%s could not be added to the workshop %s queue. Max queue reached.".formatted(client, this));
        return false;
    }

    public boolean canAddClient() {
        return queue.size() < getQueueCapacity();
    }

    public Client getNextClient() {
        if (getQueueType() == QueueType.RANDOM) {
            // TODO: implement the randomized queue.
            if (queue.isEmpty()) return null;
            return queue.stream().toList().get(this.getEngine().getRandomGenerator().nextInt(queue.size()));
        }
        return queue.peek(); // default = ORGANIZED
    }

    public boolean isOpen() {
        return now().compareTo(opening) >= 0 && now().compareTo(closing) < 0;
    }

    public boolean isClosed() {
        return now().compareTo(closing) >= 0;
    }

    @ToRecord(name = "currentClients")
    public Queue<Client> getCurrentClients() {
        return currentClients;
    }

    public void startWorkshop(Client client) {
        if (currentClients.size() >= getCapacity()) {
            if (!addClient(client)) {
                Logger.Information(this, "startWorkshop", "The workshop %s is full, the client %s is not added to the queue.".formatted(this.getType(), client.getName()));
                send(new GoToWorkshop(this.now().add(LogicalDuration.ofDay(1)), client, this.getType()));
                return;
            }
            Logger.Information(this, "startWorkshop", "The workshop %s is full, the client %s waits in the queue".formatted(this.getType(), client.getName()));
            return;
        }
        queue.removeIf(c -> c.equals(client));
        if (!currentClients.contains(client)) {
            currentClients.add(client);
        }
        // First option : the Client ends the workshop at the end of the WORKSHOP duration.
        // Logger.Information(this, "startWorkshop", "Client %s starts the workshop %s at %s for %s".formatted(client.getName(), this.getType(), this.now(), this.getDuration()));
        // send(new EndWorkshop(this.now().add(this.getDuration()), client, this));
        // Second option : the Client ends the workshop at the end of the Client required prescription duration.
        client.setWorkshopStartingTime(this.now());

        // If the workshop is a relaxation or home workshop, the client ends the workshop after 15 minutes.
        if (this.getType() == WorkshopType.RELAXATION || this.getType() == WorkshopType.HOME) {
            send(new EndWorkshop(this.now().add(LogicalDuration.ofMinutes(15)), client, this));
            Logger.Information(this, "startWorkshop", "%s starts the workshop %s at %s for %s".formatted(client.getName(), this.getType(), this.now(), LogicalDuration.ofMinutes(15)));
        }
        else {
            try {
                send(new EndWorkshop(this.now().add(client.getAttributedWorkshops().get(this.getType())), client, this));
                Logger.Information(this, "startWorkshop", "%s starts the workshop %s at %s for %s".formatted(client.getName(), this.getType(), this.now(), client.getAttributedWorkshops().get(this.getType())));
            } catch (Exception e) {
                Logger.Error(this, "startWorkshop", "%s has no more workshops to do.".formatted(client.getName()));
            }
        }
    }

    public void endWorkshop(Client client) {
        if (currentClients.contains(client)) {
            // TODO: add the efficiency to the client based on the time spent in the workshop
            // => formula: client_efficiency = (duration - timeSpent) / duration * workshop_efficiency
            // client.addEfficiency(getEfficiency());
            currentClients.remove(client);
            // If the client enters in Relaxation room, it means they have no more workshops to do.
            // Also, the Home room is only accessed when clients enter or leave the center.
            if (this.getType() != WorkshopType.RELAXATION && this.getType() != WorkshopType.HOME) {
                client.markWorkshopDone(this.getType());
                LogicalDateTime start_time = client.getWorkshopStartingTime();
                double remaining_required_time = abs(start_time.add(client.getAttributedWorkshops().get(this.getType())).subtract(this.now()).getTotalOfMinutes());
                double efficiency = 1 - (remaining_required_time / client.getAttributedWorkshops().get(this.getType()).getTotalOfMinutes());
                Logger.Information(this, "endWorkshop", "%s ends the workshop %s at %s, with efficiency %s".formatted(client.getName(), this.getType(), this.now(), efficiency));
                // Record information about the execution of the workshop.
                this.dailyEfficiency += efficiency;
                this.dailyPerfectEfficiency += 1;
                client.addWorkshopToHistory(this.getType(), efficiency);
                this.addClientToHistory(client, efficiency);
            }
            if (!client.getAttributedWorkshops().isEmpty() && !client.WorkshopsNotDone().isEmpty()) {
                List<WorkshopType> remainingWorkshops = client.WorkshopsNotDone();
                var nextWorkshopType = remainingWorkshops.get(this.getEngine().getRandomGenerator().nextInt(remainingWorkshops.size()));
                var workshopDistance = Distances.getWalkingDuration(this.getType(), nextWorkshopType);
                send(new GoToWorkshop(this.now().add(workshopDistance), client, nextWorkshopType));
            }
            else {
                send(new ClientLeavingDaily(this.now(), client));
            }
        }
        // The next client in the queue starts the workshop
        if (getNextClient() == null) return;
        send(new StartWorkshop(now(), getNextClient(), this));
    }

    public void closeWorkshop() {
        // end all current clients doing the workshop
        currentClients.forEach(client -> this.send(new EndWorkshop(this.now(), client, this)));
        if (this.getType() != WorkshopType.RELAXATION && this.getType() != WorkshopType.HOME) {
            Logger.Information(this, "closeWorkshop", "Close workshop %s at %s with efficiency %s over %s".formatted(this.getName(), this.now(), this.dailyEfficiency, this.dailyPerfectEfficiency));
        }
        else {
            Logger.Information(this, "closeWorkshop", "Close workshop %s at %s".formatted(this.getName(), this.now()));
        }
        // clear the queue and reschedule all waiting clients to the next day
        // TODO: make this depend on the workshop type (e.g. relaxation workshop doesn't have a queue) and the frequenting type => FREE/PLANNED workshops.
        queue.forEach(client -> this.send(new GoToWorkshop(this.opening.add(LogicalDuration.ofDay(1)), client, this.getType())));
        queue.clear();
        // Reset daily efficiency
        if (this.getType() != WorkshopType.RELAXATION && this.getType() != WorkshopType.HOME) {
            this.totalEfficiency.add(this.dailyEfficiency);
            this.totalPerfectEfficiency.add(this.dailyPerfectEfficiency);
            this.dailyEfficiency = 0;
            this.dailyPerfectEfficiency = 0;
        }
    }

    public void openWorkshop() {
        Logger.Information(this, "openWorkshop", "Open workshop %s at %s".formatted(this.getName(), this.now()));
        if (queue.isEmpty()) return;
        queue.forEach(client -> this.send(new StartWorkshop(this.now(), client, this)));
    }

    @Override
    public void terminate() {
        double totalEfficiency = this.totalEfficiency.stream().mapToDouble(Double::doubleValue).sum();
        long totalPerfectEfficiency = this.totalPerfectEfficiency.stream().mapToLong(Long::longValue).sum();
        Logger.Information(this, "terminate", "Terminate workshop %s at %s, total efficiency %s out of %s".formatted(this.getName(), this.now(), totalEfficiency, totalPerfectEfficiency));
        Logger.Information(this, "efficiencies", "Workshop %s has total efficiency %s".formatted(this.getName(), this.totalEfficiency));
        Logger.Information(this, "perfect efficiencies", "Workshop %s has total perfect efficiency %s".formatted(this.getName(), this.totalPerfectEfficiency));
        super.terminate();
    }

    public void increaseDailyEfficiency(double efficiency) {
        this.dailyEfficiency += efficiency;
    }

    public void increaseDailyPerfectEfficiency() {
        this.dailyPerfectEfficiency++;
    }

    public void addClientToHistory(Client client, Double efficiency) {
        double duration = abs(client.getWorkshopStartingTime().add(client.getAttributedWorkshops().get(this.getType())).subtract(this.now()).getTotalOfMinutes());
        List<Object> data = new ArrayList<>();
        data.add(client);
        data.add(efficiency);
        data.add(duration);
        data.add(this.now());
        this.workshopHistory.put(client.getWorkshopStartingTime(), data);
    }

    public HashMap<LogicalDateTime, List<Object>> getHistory() {
        return this.workshopHistory;
    }

    @Override
    public String toString() {
        return this.getType().toString();
    }
}
