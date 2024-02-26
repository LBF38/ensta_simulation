package tatooine.Client;

import engine.SimEngine;
import engine.SimEntity;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import tatooine.Events.GoToWorkshop;
import tatooine.Workshop.Distances;
import tatooine.Workshop.InitWorkshop.WorkshopType;
import tatooine.Workshop.Workshop;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@ToRecord(name = "Client")
public class Client extends SimEntity {
    private final LinkedHashMap<LogicalDateTime, ClientHistory> history = new LinkedHashMap<>();
    private List<WorkshopType> dailyWorkshops;
    private int currentEfficiency;
    private LogicalDateTime workshop_start_time;

    public Client(SimEngine engine, InitClient ini) {
        super(engine, ini);
    }

    @Override
    protected void init() {
        super.init();
        this.resetDailyWorkshops();
        var clientArrival = now().add(LogicalDuration.ofHours(7).add(LogicalDuration.ofMinutes(15)));
        var workshop = getNextWorkshop();
        var walking = clientArrival.add(Distances.getWalkingDuration(WorkshopType.HOME, workshop));
        send(new GoToWorkshop(walking, this, workshop));
    }

    @ToRecord(name = "AttributedWorkshops")
    public List<WorkshopType> getAttributedWorkshops() {
        return ((InitClient) this.getInitData()).workshops;
    }

    @ToRecord(name = "NumberOfWorkshops")
    public int getNumberOfWorkshops() {
        return getAttributedWorkshops().size();
    }

    public WorkshopType getNextWorkshop() {
        // TODO: implement a random choice for the next workshop
        return dailyWorkshops.remove(0);
    }

    public boolean hasDailyWorkshops() {
        return !dailyWorkshops.isEmpty();
    }

    public void resetDailyWorkshops() {
        dailyWorkshops = new ArrayList<>(getAttributedWorkshops());
    }

    public void updateHistory(LogicalDateTime date, Workshop workshop) {
        var client_history = new ClientHistory(workshop, getEfficiency());
        history.put(date, client_history);
    }

    public LogicalDateTime getStartFromWorkshop(Workshop workshop) {
        AtomicReference<LogicalDateTime> start = new AtomicReference<>();
        history.forEach((key, value) -> {
            if (value.workshop() == workshop) {
                start.set(key);
            }
        });
        if (start.get() == null) {
            Logger.Warning(this, "getStartFromWorkshop", "No start found for the workshop %s".formatted(workshop));
            return now();
        }
        return start.get();
    }

    public void logHistory() {
        Logger.DataSimple("Client history", "Date", "Workshop", "Efficiency");
        for (var entry : history.entrySet()) {
            Logger.DataSimple(this.getName(), entry.getKey(), entry.getValue().workshop(), entry.getValue().efficiency());
        }
    }

    public int getEfficiency() {
        return currentEfficiency;
    }

    public LogicalDateTime getWorkshopStartTime() {
        return workshop_start_time;
    }

    public void setWorkshopStartTime(LogicalDateTime time) {
        workshop_start_time = time;
    }

    @ToRecord(name = "Name")
    public String getName() {
        return this.getInitData().getName();
    }

    public void addEfficiency(long clientEfficiency) {
        this.currentEfficiency += (int) clientEfficiency;
    }
}
