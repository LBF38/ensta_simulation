package tatooine.Scenario;

import engine.ScenarioInitData;
import enstabretagne.base.time.LogicalDateTime;

public class InitScenario extends ScenarioInitData {
    public int nbClients;
    int nbEtudiants;

    public InitScenario(String name, int seed, LogicalDateTime debut, LogicalDateTime fin, int nbClients) {
        super(name, debut, fin, seed);
        this.nbClients = nbClients;
    }

    public int getEtudiants() {
        return nbEtudiants;
    }


}