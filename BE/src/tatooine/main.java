package tatooine;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.engine.SimplePlanMonitor;
import enstabretagne.helloworld.PlanHelloWorld;

public class main {
    public static void main(String[] args) {
        Logger.load();

        LogicalDateTime start = new LogicalDateTime("01/01/2024 14:00");
        LogicalDateTime end = new LogicalDateTime("01/01/2024 15:00");

        SimplePlan plan = new SimplePlan(1, start, end);

        SimplePlanMonitor spm = new SimplePlanMonitor(plan);
        spm.run();
    }
}
