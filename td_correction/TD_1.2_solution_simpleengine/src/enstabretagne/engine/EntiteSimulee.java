package enstabretagne.engine;

import enstabretagne.base.time.LogicalDateTime;

import java.util.List;
import java.util.function.Predicate;

public abstract class EntiteSimulee {
    private SimuEngine engine;
    private InitData ini;

    public EntiteSimulee(SimuEngine engine, InitData ini) {
        this.engine = engine;
        this.ini = ini;
        engine.mesEntitesSimulees.add(this);
    }

    public InitData getInit() {
        return ini;
    }

    public void Post(SimEvent ev) {
        ev.entitePorteuseEvenement = this;
        engine.Post(ev);
    }

    public LogicalDateTime Now() {
        return engine.SimulationDate();
    }

    public List<EntiteSimulee> recherche(Predicate<EntiteSimulee> query) {
        return engine.recherche(query);
    }

    public abstract void Init();
}
