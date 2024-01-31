package engine;

import enstabretagne.base.time.LogicalDateTime;

/**
 * Entité simulée.
 * A des propriétés et doit être initialisée.
 */
public abstract class EntiteSimulee {
    private final InitData initData;
    private SimuEngine engine;

    public EntiteSimulee(SimuEngine engine, InitData initData) {
        this.engine = engine;
        this.initData = initData;
        this.engine.addEntiteSimulee(this);
    }

    public InitData getInitData() {
        return this.initData;
    }

    public LogicalDateTime now() {
        return this.engine.SimulationDate();
    }

    public void send(SimEvent event) {
        send(event, null);
    }

    public void send(SimEvent event, EntiteSimulee to) {
        event.from = this;
        event.to = to;
        this.engine.addEvent(event);
    }

    public abstract void init();
}
