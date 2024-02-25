package tatooine.Scenario;

import engine.ScenarioInitData;
import enstabretagne.base.time.LogicalDateTime;

import java.util.Map;

public class InitYearScenario extends ScenarioInitData {
    public Map<Integer, Double> monthlyAttendance;
    public int maxInstituteCapacity;

    public InitYearScenario(String name, LogicalDateTime start, LogicalDateTime end, int replica, int seed, Map<Integer,Double> monthlyAttendance, int maxInstituteCapacity) {
        super(name, start, end, replica, seed);
        this.monthlyAttendance = monthlyAttendance;
        this.maxInstituteCapacity = maxInstituteCapacity;
    }
}
