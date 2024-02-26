package tatooine.Client;

import engine.SimEngine;
import engine.SimEntity;
import enstabretagne.base.logger.IRecordable;
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
    public int currentCureDay = 0;
    private List<WorkshopType> dailyWorkshops;
    private int currentEfficiency;
    private int perfect_efficiency = 0;
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
        currentCureDay++;
        dailyWorkshops = new ArrayList<>(getAttributedWorkshops());
    }

    public void updateHistory(LogicalDateTime date, Workshop workshop) {
        var client_history = new ClientHistory(workshop, getEfficiency(), currentCureDay * getDailyMaxEfficiency());
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
        Logger.Information(this, "Client History - log", "Log the client's history");
//        Logger.DataSimple("Distances - log", "Workshop1", "Workshop2", "Duration");
        for (var entry : history.entrySet()) {
            Logger.Data(new IRecordable() {
                @Override
                public String[] getTitles() {
                    return new String[]{
                            "Client",
                            "Date",
                            "Workshop",
                            "WorkshopType",
                            "WorkshopState",
                            "Efficiency",
                            "PerfectEfficiency",
                            "MaxCureEfficiency"
                    };
                }

                @Override
                public String[] getRecords() {
                    var client_history = entry.getValue();
                    return new String[]{
                            getName(),
                            entry.getKey().toString(),
                            client_history.workshop().getName(),
                            client_history.workshop().getType().toString(),
                            client_history.workshop().getWorkshopState().toString(),
                            String.valueOf(client_history.efficiency()),
                            String.valueOf(client_history.perfectEfficiency()),
                            String.valueOf(21 * getDailyMaxEfficiency())
                    };
                }

                @Override
                public String getClassement() {
                    return "Client History";
                }
            });
        }
    }

    public int getEfficiency() {
        return currentEfficiency;
    }

    public int getDailyMaxEfficiency() {
        var dailyMaxEfficiency = 0;
        for (var workshop : getAttributedWorkshops()) {
            var w = search(e -> e instanceof Workshop && ((Workshop) e).getType() == workshop);
            if (w.isEmpty()) continue;
            dailyMaxEfficiency += ((Workshop) w.get(0)).getEfficiency();
        }
        Logger.Information(this, "Client - getDailyMaxEfficiency", "Daily max efficiency: %d".formatted(dailyMaxEfficiency));
        return dailyMaxEfficiency;
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
