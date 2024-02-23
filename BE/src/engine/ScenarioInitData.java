package engine;

import enstabretagne.base.time.LogicalDateTime;

/**
 * Initialisation data model for a scenario
 */
public abstract class ScenarioInitData extends InitData {
    private LogicalDateTime start;
    private LogicalDateTime end;
    private int seed;
    private int replica;

    public ScenarioInitData(String name, LogicalDateTime start, LogicalDateTime end, int replica, int seed) {
        super(name);
        this.start = start;
        this.end = end;
        this.replica = replica;
        this.seed = seed;
    }

    public LogicalDateTime getEnd() {
        return end;
    }

    public LogicalDateTime getStart() {
        return start;
    }

    public int getSeed() {
        return seed;
    }

    public int getReplica() {
        return replica;
    }
}
