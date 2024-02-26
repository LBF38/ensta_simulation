package tatooine;

import engine.SimplePlanMonitor;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import tatooine.Plan.YearPlan;
import utils.DateTimeFrenchFormat;

/**
 * The main class of the application.
 * The simulations should be launched from that file.
 */
public class App {
    public static void main(String[] args) {
        Logger.load();

        LogicalDateTime start = new LogicalDateTime(DateTimeFrenchFormat.of(1, 3, 2024));
        LogicalDateTime end = new LogicalDateTime(DateTimeFrenchFormat.of(1, 4, 2024));

        // SimplePlan plan = new SimplePlan(2, start, end);
        //MonthPlan plan = new MonthPlan(1, start, end);
        YearPlan plan = new YearPlan(1, start, end);

        SimplePlanMonitor planMonitor = new SimplePlanMonitor(plan);
        planMonitor.run();

        Logger.Terminate();
    }
}
