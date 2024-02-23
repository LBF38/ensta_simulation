package tatooine;

import engine.SimplePlanMonitor;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import utils.DateTimeFrenchFormat;

public class App {
    public static void main(String[] args) {
        Logger.load();

        LogicalDateTime start = new LogicalDateTime(DateTimeFrenchFormat.of(1, 1, 2024, 8, 0));
        LogicalDateTime end = new LogicalDateTime(DateTimeFrenchFormat.of(1, 1, 2024, 18, 0));

        SimplePlan plan = new SimplePlan(2, start, end);

        SimplePlanMonitor planMonitor = new SimplePlanMonitor(plan);
        planMonitor.run();

        Logger.Terminate();
    }
}
