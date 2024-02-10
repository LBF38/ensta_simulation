package tatooine.Workshop;


import engine.SimEngine;
import engine.SimEntity;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class Workshop extends SimEntity {
    /**
     * The opening time of the workshop
     */
    private final LogicalDateTime opening;
    /**
     * The closing time of the workshop
     */
    private final LogicalDateTime closing;
    /**
     * The duration of the workshop
     * In minutes.
     */
    private final LogicalDuration duration;
    /**
     * The frequency of the failure
     * In days.
     */
    private final LogicalDuration failureFrequency;
    /**
     * The standard deviation of the failure frequency
     * In days.
     */
//    private final LogicalDuration failureStandardDeviation = 1; // TODO: see how to use this.
    /**
     * The duration of the failure recovery
     * In days.
     */
    private final LogicalDuration failureRecovery;

    public Workshop(SimEngine engine, InitWorkshop ini) {
        super(engine, ini);
        this.opening = new LogicalDateTime(ini.opening.toDateTimeFrenchFormat());
        this.closing = new LogicalDateTime(ini.closing.toDateTimeFrenchFormat());
        this.duration = LogicalDuration.ofMinutes(ini.duration);
        this.failureFrequency = LogicalDuration.ofDay(ini.failureFrequency);
        this.failureRecovery = LogicalDuration.ofDay(ini.failureRecovery);
    }

    public LogicalDuration getDuration() {
        return duration;
    }

    public LogicalDateTime getOpening() {
        return opening;
    }

    public LogicalDateTime getClosing() {
        return closing;
    }

    public LogicalDuration getFailureRecovery() {
        return failureRecovery;
    }

    public LogicalDuration getFailureFrequency() {
        return failureFrequency;
    }
}
