package enstabretagne.helloworld;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.engine.Plan;
import enstabretagne.engine.Scenario;
import enstabretagne.helloworld.scenario.ScenarioENSTA;
import enstabretagne.helloworld.scenario.ScenarioEnstaInitData;

import java.util.LinkedList;

//dans notre cas simple on a choisi que la date de d�but et de fin serait commune � tous les sc�narios
//mais il aurait pu en �tre autrement.
public class PlanHelloWorld extends Plan {

    LogicalDateTime debut;
    LogicalDateTime fin;

    public PlanHelloWorld(int nbReplique, LogicalDateTime debut, LogicalDateTime fin) {
        super(nbReplique);
        enstaSC = new LinkedList<>();
        this.debut = debut;
        this.fin = fin;
    }

    // liste chain�e des d�finitions de sc�narios � ex�cuter
    LinkedList<ScenarioENSTA> enstaSC;

    // cr�ation de la d�finition de chaque sc�nario
    @Override
    public void initScenarii() {
        //on cr�e autant d'instance d'un sc�nario donn� que de r�pliques souhait�es
        //on prend l'entier du num�ro de r�plique comme graine

        //premier sc�nario: sc�nario � un seul �tudiant
        for (int i = 0; i < getNbReplique(); i++)
            enstaSC.add(
                    new ScenarioENSTA(getEngine(), new ScenarioEnstaInitData("Scenario 2 Etudiants", i, debut, fin, 1)));

        //deuxi�me sc�nario: sc�nario � 3 �tudiants
        for (int i = 0; i < getNbReplique(); i++)
            enstaSC.add(new ScenarioENSTA(getEngine(),
                    new ScenarioEnstaInitData("Scenario 4 Etudiants", i, debut, fin, 3)));
    }

    //r�cup�ration de la d�finition du sc�nario suivant
    @Override
    public Scenario nextScenario() {
        if (hasNextScenario()) {
            Scenario sc = enstaSC.pop();

            //on donne le sc�nario suivant au moteur
            engine.setCurrentScenario(sc);
            return sc;
        }
        return null;
    }

    @Override
    public boolean hasNextScenario() {
        return enstaSC.size() > 0;
    }

}
