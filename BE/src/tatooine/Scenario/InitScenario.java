package tatooine.Scenario;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.engine.ScenarioInitData;

public class InitScenario extends ScenarioInitData {
    public int nbClients;
    public InitScenario(String name, int seed, LogicalDateTime debut, LogicalDateTime fin, int nbClients) {
        super(name, seed, debut, fin);
        this.nbClients = nbClients;
    }

    int nbEtudiants;

    public int getEtudiants() {
        return nbEtudiants;
    }


}