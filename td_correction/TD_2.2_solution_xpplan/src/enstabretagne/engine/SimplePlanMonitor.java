package enstabretagne.engine;

import enstabretagne.base.logger.Logger;

/**
 * exemple d'un moniteur ultra simple
 */
//c'est lui qui porte le cadencement des sc�narios via le plan d'exp�rience
public class SimplePlanMonitor {
    Plan plan;
    SimuEngine engine;

    public SimplePlanMonitor(Plan p) {
        //il porte la cr�ation du moteur
        this.engine = new SimuEngine();

        plan = p;
        plan.engine = engine;
    }

    //m�thode principale simple
    public void run() {
        //1. on cr�e les sc�narii
        plan.initScenarii();
        Logger.Information(this, "main", "D�but du plan d'exp�rience");

        //on boucle sur les sc�narios
        while (plan.hasNextScenario()) {
            //� chaque sc�nario on demande la cr�ation des entit�s de simulation correspondantes
            plan.nextScenario().creerEntitesSimulees();

            //la cr�ation des entit�s a �t� port�e � la connaissance du moteur
            //noter que de nouvelles entit�s pourront �tre cr��es au cours de la simulation
            engine.initSimulation();
            //d�clenchement de la boucle de simulation
            engine.simulate();
            //fin du run, nettoyage du moteur en vue de la possible prochaine ex�cution de sc�nario
            engine.terminate(false);//ce n'est pas la derni�re ex�cution
        }
        engine.terminate(true);
        Logger.Information(null, "main", "Fin du plan d'exp�rience");

    }

}
