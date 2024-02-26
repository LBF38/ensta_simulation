package tatooine.Events;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import tatooine.Workshop.Workshop;

/**
 * The event of a workshop recovering from a failure.
 */
public class RecoveringWorkshop extends SimEvent<Workshop> {
    public RecoveringWorkshop(LogicalDateTime occurrenceDate, Workshop workshop) {
        super(occurrenceDate, workshop, null);
    }

    @Override
    public void process() {
        Logger.Information(this, "RecoveringWorkshop Event process", "Recovering workshop at " + this.getOccurrenceDate());
        this.from.recoverFailure();
    }
}
