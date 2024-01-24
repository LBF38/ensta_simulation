package engine;

import enstabretagne.base.time.LogicalDateTime;

/**
 * Evénement simulé.
 * A une date d'occurrence et est associé à une entité simulée.
 */
public abstract class SimEvent implements Comparable<SimEvent> {
    public LogicalDateTime occurrenceDate;
    public EntiteSimulee from;
    public EntiteSimulee to;

    public SimEvent(LogicalDateTime occurrenceDate, EntiteSimulee entiteSimulee, EntiteSimulee to) {
        this.occurrenceDate = occurrenceDate;
        this.from = entiteSimulee;
        this.to = to;
    }

    public abstract void process();

    @Override
    public int compareTo(SimEvent other) {
        return this.occurrenceDate.compareTo(other.occurrenceDate);
    }
}
