package engine;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.basics.ScenarioId;

/**
 * Entité simulée particulière.
 * Le scénario est la mère de toutes les entités.
 * Utile si on veut créer des tests particuliers ou faire des plans d'expérience.
 * Il a une méthode particulière de création d'entités simulées.
 * Il expose les données génériques d'initialisation d'un scénario à savoir la date de début de fin et la graine.
 */
public abstract class Scenario extends SimEntity {
    /**
     * Identifiant du scénario.
     * On l'identifie par un nom sous forme de chaine de caractère.
     * Il est porteur de la graine utilisée dans le cadre d'une réplique.
     */
    ScenarioId id;
    private int seed;

    public Scenario(SimEngine engine, ScenarioInitData init) {
        super(engine, init);
        id = new ScenarioId(init.getName(), init.getReplica());
        seed = ((ScenarioInitData) getInitData()).getSeed();
    }

    public LogicalDateTime getStart() {
        return ((ScenarioInitData) getInitData()).getStart();
    }

    public LogicalDateTime getEnd() {
        return ((ScenarioInitData) getInitData()).getEnd();
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        id.setRepliqueNumber(seed);
        this.seed = seed;
    }

    ScenarioId getID() {
        return id;
    }

    public abstract void createSimulatedEntities();
}
