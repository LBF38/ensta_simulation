package tatooine.Events;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import tatooine.Workshop.Workshop;

public class CloseWorkshop extends SimEvent<Workshop> {
    public CloseWorkshop(LogicalDateTime occurrenceDate, Workshop workshop) {
        super(occurrenceDate, workshop, null);
    }

    @Override
    public void process() {
        Logger.Information(this, "CloseWorkshop Event process", "Close workshop %s at %s".formatted(this.from.getName(), this.getOccurrenceDate()));
        // TODO: make all current clients leave the workshop.
        if (this.from.isFailure()) {
            Logger.Information(this, "CloseWorkshop Event process", "Workshop %s is in failure, please wait workshop recovery".formatted(this.from.getName()));
            return;
        }
        this.from.closeWorkshop();
        this.rescheduleAt(this.getOccurrenceDate().add(LogicalDuration.ofDay(1)));
    }
}
