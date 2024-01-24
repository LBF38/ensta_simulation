package engine;

import enstabretagne.base.time.LogicalDateTime;

/**
 * Evénement simulé.
 * A une date d'occurrence et est associé à une entité simulée.
 */
public abstract class SimEvent implements Comparable<SimEvent> {
    public LogicalDateTime occurrenceDate;
    public EntiteSimulee entiteSimulee;

    public SimEvent(LogicalDateTime occurrenceDate, EntiteSimulee entiteSimulee) {
        this.occurrenceDate = occurrenceDate;
        this.entiteSimulee = entiteSimulee;
    }

    public abstract void process();

    @Override
    public int compareTo(SimEvent other) {
        return this.occurrenceDate.compareTo(other.occurrenceDate);
    }
}
