package engine;

import enstabretagne.base.logger.Logger;

/**
 * example of a very simple monitor
 * it carries the timing of the scenarios via the experiment plan
 */
public class SimplePlanMonitor {
    Plan plan;
    SimEngine engine;

    public SimplePlanMonitor(Plan p) {
        // Create the engine.
        this.engine = new SimEngine();

        plan = p;
        plan.engine = engine;
    }

    /**
     * Run the plan.
     */
    public void run() {
        // Create the scenarios.
        plan.initScenarii();
        Logger.Information(this, "main", "Start of the experience plan");

        // Scenarios loop.
        while (plan.hasNextScenario()) {
            // for each scenario, we ask for the creation of the corresponding simulated entities
            plan.nextScenario().createSimulatedEntities();

            // Start the engine. & requestInit() of the scenario and all its entities
            engine.init();
            // Start the simulation.
            engine.simulate();
            // End of this scenario.
            engine.clean();
        }
        engine.terminate();
        Logger.Information(null, "main", "End of the experience plan");
    }

}
