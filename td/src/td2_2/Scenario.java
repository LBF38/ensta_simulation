package td2_2;

import engine.InitData;
import engine.SimEngine;
import engine.SimEntity;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.basics.ScenarioId;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Scenario extends SimEntity {
    private final double seed;
    private final LogicalDateTime start;
    private final LogicalDateTime end;
    private final Function<Void, List<SimEntity>> createEntities;
    protected final ScenarioId id;

    public Scenario(SimEngine engine, InitData initData, double seed, LogicalDateTime start, LogicalDateTime end, Function<Void, List<SimEntity>> createEntities, ScenarioId id) {
        super(engine, initData);
        this.seed = seed;
        this.start = start;
        this.end = end;
        this.createEntities = createEntities;
        this.id = id; // TODO: generate a unique id

    }

    @Override
    public void init() {
        List<SimEntity> entities = this.createEntities.apply(null);
        entities.forEach(SimEntity::init);
    }

    public double getSeed() {
        return seed;
    }

    public LogicalDateTime getStart() {
        return start;
    }

    public LogicalDateTime getEnd() {
        return end;
    }
}
