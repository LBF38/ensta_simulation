package engine;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.basics.ISimulationDateProvider;
import enstabretagne.simulation.basics.SortedList;

import java.util.ArrayList;

public class SimuEngine implements ISimulationDateProvider {
    private final LogicalDateTime start;
    private final LogicalDateTime end;
    private final SortedList<SimEvent> events = new SortedList<>();
    protected ArrayList<EntiteSimulee> simulatedEntities = new ArrayList<>();
    private LogicalDateTime current;

    public SimuEngine(LogicalDateTime startDate, LogicalDateTime endDate) {
        this.start = startDate;
        this.current = this.start;
        this.end = endDate;
    }

    public void addEntiteSimulee(EntiteSimulee entiteSimulee) {
        this.simulatedEntities.add(entiteSimulee);
    }

    protected void addEvent(SimEvent event) {
        this.events.add(event);
    }

    public void init() {
        for (EntiteSimulee e : simulatedEntities) {
            Logger.Detail(this, "init", "entity init = " + e);
            e.init();
        }
    }

    public void simulate() {
        Logger.Information(this, "simulate", "current = " + this.current);
        while (hasNextEvent()) {
            Logger.Information(this, "simulate", "events.size() = " + this.events.size());
            SimEvent event = this.events.first();
            this.events.remove(event);
            current = event.getOccurrenceDate();
            Logger.Information(this, "simulate", "event = " + event);
            event.process();
            Logger.Information(this, "simulate", "currentDate = " + this.current);
        }
    }

    private boolean hasNextEvent() {
        for (SimEvent e : events) {
            if (e.getOccurrenceDate().compareTo(end) <= 0) {
                Logger.Information(this, "hasNextEvent", "true");
                return true;
            }
        }
        Logger.Information(this, "hasNextEvent", "false");
        return false;
    }

    @Override
    public LogicalDateTime SimulationDate() {
        return current;
    }
}
