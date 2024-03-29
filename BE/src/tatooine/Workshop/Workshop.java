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
import utils.DateTimeFrenchFormat;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The useful parts of the institute : places where clients can do activities.
 */
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
    /**
     * The initial data of the workshop
     */
    private final InitWorkshop initData;
    private final Queue<Client> queue = new LinkedList<>();
    private final Queue<Client> currentClients = new LinkedList<>();
    private WorkshopState workshopState = WorkshopState.NONE;

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

    @ToRecord(name = "workshop state")
    public WorkshopState getWorkshopState() {
        return workshopState;
    }

    public void setWorkshopState(WorkshopState state) {
        this.workshopState = state;
    }

    @Override
    protected void init() {
        super.init();
        send(new OpenWorkshop(this.opening, this));
        var failureFrequency = getRandomFailureFrequency();
        Logger.Detail(this, "init", "Workshop %s failure frequency: %s".formatted(this.getType(), failureFrequency));
        if (getType() != WorkshopType.RELAXATION && getType() != WorkshopType.HOME)
            send(new WorkshopFailure(this.now().add(failureFrequency), this));
        // TODO: add the next client event => see how to do this.
        send(new CloseWorkshop(this.closing, this));
    }

    /**
     * Get the random failure frequency based on the failure frequency and the standard deviation
     *
     * @return the random failure frequency
     */
    private LogicalDuration getRandomFailureFrequency() {
        double mean = getFailureFrequency().DoubleValue();
        double stdDev = getFailureStandardDeviation().DoubleValue();
        double nextFailure = this.getEngine().getRandomGenerator().nextGaussian(mean, stdDev);
        return LogicalDuration.ofSeconds(nextFailure);
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

    /**
     * Add a client to the workshop queue
     *
     * @param client the client to add
     * @return true if the client was added, false otherwise
     */
    public boolean addClient(Client client) {
        if (canAddClient()) {
            Logger.Detail(this, "addClient", "%s added to the workshop %s queue.".formatted(client.getName(), this));
            queue.add(client);
            return true;
        }
        Logger.Detail(this, "addClient", "%s could not be added to the workshop %s queue. Max queue reached.".formatted(client.getName(), this));
        return false;
    }

    /**
     * Check if the workshop can add one client to its queue
     *
     * @return true if the workshop can add a client, false otherwise
     */
    public boolean canAddClient() {
        return queue.size() < getQueueCapacity();
    }

    /**
     * Get the next client from the queue into the workshop
     *
     * @return the next client in the queue
     */
    public Client getNextClient() {
        if (getQueueType() == QueueType.RANDOM) {
            // TODO: implement the randomized queue.
            if (queue.isEmpty()) return null;
            return queue.stream().toList().get(this.getEngine().getRandomGenerator().nextInt(queue.size()));
        }
        return queue.peek(); // default = ORGANIZED
    }

    public boolean isOpen() {
        return getWorkshopState() == WorkshopState.OPEN;
    }

    public boolean isClosed() {
        return getWorkshopState() == WorkshopState.CLOSED;
    }

    @ToRecord(name = "currentClients")
    public Queue<Client> getCurrentClients() {
        return currentClients;
    }

    /**
     * Makes a Client start the workshop
     *
     * @param client the client that accesses the workshop
     */
    public void startWorkshop(Client client) {
        // If the workshop is in failure, the client is not added to the queue, and will try again next day.
        if (getWorkshopState() == WorkshopState.FAILURE) {
            Logger.Information(this, "startWorkshop", "The workshop %s is in failure, the client %s is not added to the queue.".formatted(this.getType(), client.getName()));
            send(new GoToWorkshop(this.now().add(LogicalDuration.ofDay(1)), client, this.getType()));
            return;
        }

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
        if (!currentClients.contains(client)) currentClients.add(client);
        client.updateHistory(now(), this); // starts the workshop
        client.setWorkshopStartTime(this.now());

        Logger.Information(this, "startWorkshop", "%s starts the workshop %s at %s for %s".formatted(client.getName(), this.getType(), this.now(), this.getDuration()));
        send(new EndWorkshop(now().add(getDuration()), client, this));
    }

    /**
     * Makes a Client end and leave the workshop
     *
     * @param client the client that ends the workshop
     */
    public void endWorkshop(Client client) {
        if (currentClients.contains(client)) {
            // => formula: client_efficiency = (duration - timeSpent) / duration * workshop_efficiency
            var start = client.getWorkshopStartTime();
            var client_efficiency = this.now().subtract(start).getTotalOfMinutes() / getDuration().getTotalOfMinutes() * getEfficiency();
            Logger.Detail(this, "endWorkshop", "%s efficiency: %s".formatted(client.getName(), client_efficiency));
            client.addEfficiency(client_efficiency);

            currentClients.remove(client);
            client.updateHistory(now(), this); // finishes the workshop

            Logger.Information(this, "endWorkshop", "%s ends the workshop %s at %s".formatted(client.getName(), this.getType(), this.now()));

            // The client has finished all his workshops for today : their next workshop is tomorrow.
            if (!client.hasDailyWorkshops()) {
                Logger.Information(this, "endWorkshop", "The client %s has finished all his workshops for today.".formatted(client.getName()));
                client.resetDailyWorkshops();
                var client_next_workshop = client.getNextWorkshop();
                var nextWorkshop = this.getEngine().search(e -> e instanceof Workshop && ((Workshop) e).getType() == client_next_workshop);
                if (nextWorkshop.isEmpty()) {
                    Logger.Information(this, "endWorkshop", "No workshop %s found for the client %s".formatted(client.getNextWorkshop(), client.getName()));
                    return;
                }
                var w = (Workshop) nextWorkshop.get(0);
                send(new GoToWorkshop(w.getNextOpening(), client, w.getType()));
                return;
            }
            // The client has other workshops for today : their next workshop is the next one in their to-do list.
            var nextWorkshopType = client.getNextWorkshop();
            var workshopDistance = Distances.getWalkingDuration(this.getType(), nextWorkshopType);
            send(new GoToWorkshop(this.now().add(workshopDistance), client, nextWorkshopType));
        }
        // The next client in the queue (if any) starts the workshop
        if (getNextClient() == null) return;
        send(new StartWorkshop(now(), getNextClient(), this));
    }

    /**
     * Close the workshop, either at the closing time, or after a failure
     */
    public void closeWorkshop() {
        Logger.Information(this, "closeWorkshop", "Close workshop %s at %s".formatted(this.getName(), this.now()));
        // update workshop state
        this.setWorkshopState(WorkshopState.CLOSED);
        // end all current clients doing the workshop
        currentClients.forEach(client -> this.send(new EndWorkshop(this.now(), client, this)));
        // clear the queue and reschedule all waiting clients to the next day
        // TODO: make this depend on the workshop type (e.g. relaxation workshop doesn't have a queue) and the frequenting type => FREE/PLANNED workshops.
        queue.forEach(client -> this.send(new GoToWorkshop(getNextOpening(), client, this.getType())));
        queue.clear();
    }

    public boolean isFailure() {
        return getWorkshopState() == WorkshopState.FAILURE;
    }

    public boolean isRecovering() {
        return getWorkshopState() == WorkshopState.RECOVERING;
    }

    /**
     * Open the workshop (either after a recovery, or at the opening time of the workshop
     */
    public void openWorkshop() {
        Logger.Information(this, "openWorkshop", "Open workshop %s at %s".formatted(this.getName(), this.now()));
        // update workshop state
        if (getWorkshopState() == WorkshopState.FAILURE) return;
        this.setWorkshopState(WorkshopState.OPEN);
        // start the workshop for the clients in the queue
        if (queue.isEmpty()) return;
        queue.forEach(client -> this.send(new StartWorkshop(this.now(), client, this)));
    }

    /**
     * Start the failure of this workshop.
     * The workshop will be closed for the duration of the failure recovery.
     */
    public void startFailure() {
        if (getType() == WorkshopType.RELAXATION || getType() == WorkshopType.HOME)
            return; // these workshops can't fail (it's infinite)

        Logger.Information(this, "startFailure", "Workshop %s failure at %s".formatted(this.getName(), this.now()));

        // update workshop state
        this.setWorkshopState(WorkshopState.FAILURE);

        // close the workshop
        currentClients.forEach(client -> this.send(new EndWorkshop(this.now(), client, this)));
        queue.forEach(client -> this.send(new GoToWorkshop(this.now().add(getFailureRecovery()), client, this.getType())));
        queue.clear();

        // schedule the recovery
        send(new RecoveringWorkshop(this.now().add(getFailureRecovery()), this));
    }

    /**
     * Recover the failure of this workshop.
     * The workshop will be opened again.
     */
    public void recoverFailure() {
        Logger.Information(this, "recoverFailure", "Workshop %s recovered at %s".formatted(this.getName(), this.now()));

        // update workshop state
        this.setWorkshopState(WorkshopState.RECOVERING);

        // reschedule the opening and closing of the workshop
        send(new OpenWorkshop(getNextOpening(), this));
        send(new CloseWorkshop(getNextClosing(), this));
    }

    private LogicalDateTime getNextClosing() {
        return new LogicalDateTime(DateTimeFrenchFormat.of(this.now().add(LogicalDuration.ofDay(1)).truncateToDays(), getClosing().getHour(), getClosing().getMinute()));
    }

    private LogicalDateTime getNextOpening() {
        return new LogicalDateTime(DateTimeFrenchFormat.of(this.now().add(LogicalDuration.ofDay(1)).truncateToDays(), getOpening().getHour(), getOpening().getMinute()));
    }
}
