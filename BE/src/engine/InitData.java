package engine;

/**
 * Initialisation data for an entity.
 */
public abstract class InitData {
    private final String name;

    public InitData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
