package engine;

import enstabretagne.base.time.LogicalDateTime;

/**
 * Evénement simulé.
 * A une date d'occurrence et est associé à une entité simulée.
 */
public abstract class SimEvent implements Comparable<SimEvent> {
    protected EntiteSimulee from;
    protected EntiteSimulee to;
    private LogicalDateTime occurrenceDate;

    public SimEvent(LogicalDateTime occurrenceDate, EntiteSimulee entiteSimulee, EntiteSimulee to) {
        this.occurrenceDate = occurrenceDate;
        this.from = entiteSimulee;
        this.to = to;
    }

    public abstract void process();

    protected LogicalDateTime getOccurrenceDate() {
        return this.occurrenceDate;
    }

    protected LogicalDateTime rescheduleAt(LogicalDateTime newDate) {
        this.occurrenceDate = newDate;
        return this.occurrenceDate;
    }

    @Override
    public int compareTo(SimEvent other) {
        return this.occurrenceDate.compareTo(other.occurrenceDate);
    }
}
