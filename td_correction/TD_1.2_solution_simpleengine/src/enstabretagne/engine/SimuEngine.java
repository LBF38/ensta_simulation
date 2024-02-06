package enstabretagne.engine;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.basics.ISimulationDateProvider;
import enstabretagne.simulation.basics.SortedList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SimuEngine implements ISimulationDateProvider {

    private SortedList<SimEvent> echeancier;
    private LogicalDateTime start;
    private LogicalDateTime end;
    private LogicalDateTime currentDate;
    protected List<EntiteSimulee> mesEntitesSimulees;

    public SimuEngine() {
        echeancier = new SortedList<>();
        mesEntitesSimulees = new ArrayList<>();
    }

    public void initSimulation(LogicalDateTime start, LogicalDateTime end) {
        this.start = start;
        currentDate = this.start;
        this.end = end;

        for (EntiteSimulee e : mesEntitesSimulees) {
            e.Init();
        }
    }

    protected void Post(SimEvent ev) {
        echeancier.add(ev);
    }

    public void simulate() {
        while (hasANextEvent()) {
            SimEvent ev = echeancier.first();
            echeancier.remove(ev);
            currentDate = ev.getDateOccurence();
            ev.process();
        }
    }

    private boolean hasANextEvent() {
        for (SimEvent e : echeancier) {
            if (e.getDateOccurence().compareTo(end) <= 0)
                return true;
        }
        return false;
    }

    public List<EntiteSimulee> recherche(Predicate<EntiteSimulee> query) {
        List<EntiteSimulee> resultats = new ArrayList<>();
        for (EntiteSimulee e : mesEntitesSimulees) {
            if (query.test(e))
                resultats.add(e);
        }
        return resultats;
    }

    @Override
    public LogicalDateTime SimulationDate() {
        return currentDate;
    }
}
