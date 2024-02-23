package tatooine;

import engine.SimplePlanMonitor;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;

public class App {
    public static void main(String[] args) {
        Logger.load();

        LogicalDateTime start = new LogicalDateTime("01/01/2024 14:00");
        LogicalDateTime end = new LogicalDateTime("01/01/2024 15:00");

        SimplePlan plan = new SimplePlan(2, start, end);

        SimplePlanMonitor spm = new SimplePlanMonitor(plan);
        spm.run();

        Logger.Terminate();
    }
}
