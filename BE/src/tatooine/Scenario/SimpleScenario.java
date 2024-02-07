package tatooine.Scenario;

import enstabretagne.base.logger.Logger;
import enstabretagne.engine.Scenario;
import enstabretagne.engine.SimuEngine;

public class SimpleScenario extends Scenario {
    public SimpleScenario(SimuEngine engine, InitScenario init) {
        super(engine, init);
    }
    int totalEntities;

    @Override
    public void creerEntitesSimulees() {
        Logger.Information(this, "CreateEntities", "test");
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
}
