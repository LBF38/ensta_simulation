package tatooine;

import engine.SimplePlanMonitor;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import tatooine.Plan.MonthPlan;
import utils.DateTimeFrenchFormat;

public class App {
    public static void main(String[] args) {
        Logger.load();

        LogicalDateTime start = new LogicalDateTime(DateTimeFrenchFormat.of(1, 1, 2024));
        LogicalDateTime end = new LogicalDateTime(DateTimeFrenchFormat.of(1, 2, 2024));

        // SimplePlan plan = new SimplePlan(2, start, end);
        MonthPlan plan = new MonthPlan(2, start, end);

        SimplePlanMonitor planMonitor = new SimplePlanMonitor(plan);
        planMonitor.run();

        Logger.Terminate();
    }
}
