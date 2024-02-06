package engine;

import enstabretagne.base.time.LogicalDateTime;

import java.util.List;
import java.util.function.Predicate;

/**
 * Entité simulée.
 * A des propriétés et doit être initialisée.
 */
public abstract class SimEntity {
    protected final InitData initData;
    protected final SimEngine engine;

    public SimEntity(SimEngine engine, InitData initData) {
        this.engine = engine;
        this.initData = initData;
        this.engine.addEntity(this);
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

    public void send(SimEvent event, SimEntity to) {
        event.from = this;
        event.to = to;
        this.engine.addEvent(event);
    }

    public abstract void init();

    public List<SimEntity> search(Predicate<SimEntity> predicate) {
        return this.engine.search(predicate);
    }
}
