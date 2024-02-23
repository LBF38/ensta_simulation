package tatooine.Scenario;

import engine.ScenarioInitData;
import enstabretagne.base.time.LogicalDateTime;

public class InitScenario extends ScenarioInitData {
    public int nbClients;

    public InitScenario(String name, int replica, int seed, LogicalDateTime debut, LogicalDateTime fin, int nbClients) {
        super(name, debut, fin, replica, seed);
        this.nbClients = nbClients;
    }
}