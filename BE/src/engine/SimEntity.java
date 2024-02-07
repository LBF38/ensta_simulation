package engine;

import enstabretagne.base.time.LogicalDateTime;

import java.util.List;
import java.util.function.Predicate;

/**
 * Entité simulée.
 * A des propriétés et doit être initialisée.
 */
public abstract class SimEntity {
    private final InitData initData;
    private final SimEngine engine;
    private EntityState state;

    public SimEntity(SimEngine engine, InitData initData) {
        this.state = EntityState.NONE;
        this.engine = engine;
        this.initData = initData;
        this.engine.addEntity(this);
    }

    protected EntityState getState() {
        return state;
    }

    public InitData getInitData() {
        return initData;
    }

    public SimEngine getEngine() {
        return engine;
    }

    public String getName() {
        return initData.getName();
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

    public void requestInit() {
        if (state != EntityState.NONE) {
            return;
        }
        init();
    }

    protected void init() {
        state = EntityState.INITIALIZED;
    }

    /**
     * Method for terminating the entity.
     * Should be overridden to release resources.
     */
    protected void terminate() {
        state = EntityState.DEAD;
    }

    public List<SimEntity> search(Predicate<SimEntity> predicate) {
        return this.engine.search(predicate);
    }
}
