package tatooine.Events;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import tatooine.Workshop.Workshop;

public class WorkshopFailure extends SimEvent<Workshop> {
    public WorkshopFailure(LogicalDateTime occurrenceDate, Workshop workshop) {
        super(occurrenceDate, workshop, null);
    }

    @Override
    public void process() {
        Logger.Information(this, "WorkshopFailure Event process", "Workshop failure at " + this.getOccurrenceDate());
        this.from.startFailure();
    }
}
