package tatooine.Workshop;


import engine.SimEngine;
import engine.SimEntity;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

@ToRecord(name = "Workshop")
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
    private final LogicalDuration failureStandardDeviation; // TODO: see how to use this.
    /**
     * The duration of the failure recovery
     * In days.
     */
    private final LogicalDuration failureRecovery;
    private final InitWorkshop initData;

    public Workshop(SimEngine engine, InitWorkshop ini) {
        super(engine, ini);
        this.opening = new LogicalDateTime(ini.opening.toDateTimeFrenchFormat());
        this.closing = new LogicalDateTime(ini.closing.toDateTimeFrenchFormat());
        this.duration = LogicalDuration.ofMinutes(ini.duration);
        this.failureFrequency = LogicalDuration.ofDay(ini.failureFrequency);
        this.failureRecovery = LogicalDuration.ofDay(ini.failureRecovery);
        this.failureStandardDeviation = LogicalDuration.ofDay(ini.failureStandardDeviation);
        this.initData = ini;
    }

    @ToRecord(name = "duration")
    public LogicalDuration getDuration() {
        return duration;
    }

    @ToRecord(name = "opening")
    public LogicalDateTime getOpening() {
        return opening;
    }

    @ToRecord(name = "closing")
    public LogicalDateTime getClosing() {
        return closing;
    }

    @ToRecord(name = "failure recovery")
    public LogicalDuration getFailureRecovery() {
        return failureRecovery;
    }

    @ToRecord(name = "failure frequency")
    public LogicalDuration getFailureFrequency() {
        return failureFrequency;
    }

    @ToRecord(name = "failure standard deviation")
    public LogicalDuration getFailureStandardDeviation() {
        return failureStandardDeviation;
    }

    @ToRecord(name = "capacity")
    public int getCapacity() {
        return initData.capacity;
    }

    @ToRecord(name = "efficiency")
    public int getEfficiency() {
        return initData.efficiency;
    }

    @ToRecord(name = "frequenting")
    public InitWorkshop.Frequenting getFrequenting() {
        return initData.frequenting;
    }

    @ToRecord(name = "queueCapacity")
    public int getQueueCapacity() {
        return initData.queueCapacity;
    }

    @ToRecord(name = "queueType")
    public InitWorkshop.QueueType getQueueType() {
        return initData.queueType;
    }
}
