package engine;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.basics.SortedList;

import java.util.ArrayList;

public class SimuEngine {
    public ArrayList<EntiteSimulee> entitesSimulees = new ArrayList<>();
    public SortedList<SimEvent> events = new SortedList<>();
    public LogicalDateTime startDate;
    public LogicalDateTime endDate;

    public SimuEngine(LogicalDateTime startDate, LogicalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addEntiteSimulee(EntiteSimulee entiteSimulee) {
        this.entitesSimulees.add(entiteSimulee);
    }

    public void addEvent(SimEvent event) {
        this.events.add(event);
    }

    public void run() {
        Logger.load();
        while (this.events.size() != 0) {
            Logger.Information(this, "run", "events.size() = " + this.events.size());
            SimEvent event = this.events.first();
            event.process();
            this.events.remove(event);
            Logger.Information(this, "run", "events.size() = " + this.events.size());
        }
        Logger.Terminate();
    }
}
