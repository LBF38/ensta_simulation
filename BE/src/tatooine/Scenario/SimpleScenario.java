package tatooine.Scenario;

import engine.Scenario;
import engine.SimEngine;
import enstabretagne.base.logger.Logger;

/**
 * A very simple scenario with no modification of affluence or number of clients during its execution.
 */
public class SimpleScenario extends Scenario {
    int totalEntities;

    public SimpleScenario(SimEngine engine, InitScenario init) {
        super(engine, init);
    }

    @Override
    public void init() {
        super.init();
    }


    @Override
    public void terminate() {
        super.terminate();
        totalEntities = 0;
    }

    @Override
    public void createSimulatedEntities() {
        Logger.Information(this, "CreateEntities", "test");
    }
}
