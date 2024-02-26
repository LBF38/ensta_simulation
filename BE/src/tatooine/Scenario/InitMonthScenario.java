package tatooine.Scenario;

import engine.ScenarioInitData;
import enstabretagne.base.time.LogicalDateTime;

/**
 * The initial data of a month scenario.
 */
public class InitMonthScenario extends ScenarioInitData {
    public MonthlyAffluence affluence;
    public int maxInstituteCapacity;

    public InitMonthScenario(String name, LogicalDateTime start, LogicalDateTime end, int replica, int seed, MonthlyAffluence affluence, int maxInstituteCapacity) {
        super(name, start, end, replica, seed);
        this.affluence = affluence;
        this.maxInstituteCapacity = maxInstituteCapacity;
    }
}
