package tatooine.Events;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import tatooine.Workshop.Workshop;

public class OpenWorkshop extends SimEvent {
    public OpenWorkshop(LogicalDateTime occurrenceDate, Workshop workshop) {
        super(occurrenceDate, workshop, null);
    }

    @Override
    public void process() {
        Logger.Information(this, "OpenWorkshop Event process", "Open workshop %s at %s".formatted(this.from.getName(), this.getOccurrenceDate()));
    }
}
