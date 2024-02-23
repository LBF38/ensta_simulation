package engine;

import enstabretagne.base.time.LogicalDateTime;

/**
 * Evénement simulé.
 * A une date d'occurrence et est associé à une entité simulée.
 */
public abstract class SimEvent<From extends SimEntity> implements Comparable<SimEvent<From>> {
    protected From from;
    protected SimEntity to;
    private LogicalDateTime occurrenceDate;

    public SimEvent(LogicalDateTime occurrenceDate, From from, SimEntity to) {
        this.occurrenceDate = occurrenceDate;
        this.from = from;
        this.to = to;
    }

    public abstract void process();

    protected LogicalDateTime getOccurrenceDate() {
        return this.occurrenceDate;
    }

    protected void rescheduleAt(LogicalDateTime newDate) {
        this.occurrenceDate = newDate;
        this.from.getEngine().addEvent((SimEvent<SimEntity>) this); // Reschedule the event.
    }

    @Override
    public int compareTo(SimEvent other) {
        return this.occurrenceDate.compareTo(other.occurrenceDate);
    }
}
