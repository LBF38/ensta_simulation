package enstabretagne.helloworld.scenario;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.engine.ScenarioInitData;

//le crit�re de variation entre deux sc�narios diff�rent sera juste le nombre d'�tudiant
//in aurait pu choisir des choses plus complexes comme un nombre d'homme, de femme, de autre...
public class ScenarioEnstaInitData extends ScenarioInitData {

    public ScenarioEnstaInitData(String name, int graine, LogicalDateTime debut, LogicalDateTime fin, int nbEtudiants) {
        super(name, graine, debut, fin);
        this.nbEtudiants = nbEtudiants;
    }

    int nbEtudiants;

    public int getEtudiants() {
        return nbEtudiants;
    }


}
