package tatooine.Scenario;

import engine.ScenarioInitData;
import enstabretagne.base.time.LogicalDateTime;

public class InitMonthScenario extends ScenarioInitData {
    public MonthlyAffluence affluence;
    public int maxInstituteCapacity;

    public InitMonthScenario(String name, LogicalDateTime start, LogicalDateTime end, int seed, MonthlyAffluence affluence, int maxInstituteCapacity) {
        super(name, start, end, seed);
        this.affluence = affluence;
        this.maxInstituteCapacity = maxInstituteCapacity;
    }
}
