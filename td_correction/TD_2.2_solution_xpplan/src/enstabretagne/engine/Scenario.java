package enstabretagne.engine;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.basics.ScenarioId;

/**
 * entit� simul�e particuli�re
 * le sc�nario est la m�re de toutes les entit�s
 * utile si on veut cr�er des tests particuliers ou faire des plans d'exp�rience
 * il a une m�thode particuli�re de cr�ation d'entit�s simul�es
 * il expose les donn�es g�n�riques d'initialisation d'un sc�nario � savoir la date de d�but de fin et la graine
 */
public abstract class Scenario extends EntiteSimulee {

    public LogicalDateTime getDebut() {
        return ((ScenarioInitData) getInit()).getDebut();
    }

    public LogicalDateTime getFin() {
        return ((ScenarioInitData) getInit()).getFin();
    }


    public Scenario(SimuEngine engine, ScenarioInitData init) {
        super(engine, init);
        id = new ScenarioId(init.getName(), init.getGraine());
        graine = ((ScenarioInitData) getInit()).getGraine();
    }

    //gestion de la graine
    private int graine;

    public int getGraine() {
        return graine;
    }

    public void setGraine(int graine) {
        id.setRepliqueNumber(graine);
        this.graine = graine;
    }

    //identifiant du sc�nario
    //on l'identifie par un nom sous forme de chaine de caract�re
    //il est porteur de la graine utilis�e dans le cadre d'une r�plique
    ScenarioId id;

    ScenarioId getID() {
        return id;
    }

    public abstract void creerEntitesSimulees();

}
