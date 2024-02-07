package engine;

/**
 * le plan tel que propos� dans ce TD est d�fini comme un g�n�rateur de sc�narii
 * il comprend 3 m�thodes abstraites � sp�cialiser
 */

public abstract class Plan {

    protected SimEngine engine;
    //nombre de fois qu'un m�me sc�nario sera ex�cut� mais avec une graine diff�rente
    private int replicas;
    /**
     * Current replica number.
     */
    private int currentReplica;

    public Plan(int replicas) {
        this.replicas = replicas;
    }

    public int getReplicas() {
        return replicas;
    }

    public SimEngine getEngine() {
        return engine;
    }

    public int getCurrentReplica() {
        return currentReplica;
    }

    /**
     * Initialize the scenarios.
     */
    public abstract void initScenarii();

    /**
     * Whether there is still a scenario to execute.
     *
     * @return true if there is still a scenario to execute, false otherwise.
     */
    public abstract boolean hasNextScenario();
    
    /**
     * Get the next scenario.
     *
     * @return the next scenario if there is one, null otherwise.
     */
    public abstract Scenario nextScenario();

}
