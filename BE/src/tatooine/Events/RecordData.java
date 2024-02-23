package tatooine.Events;

import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import tatooine.Client.Client;

public class RecordData extends SimEvent {
    public RecordData(LogicalDateTime occurrenceDate, SimEntity simEntity) {
        super(occurrenceDate, simEntity, null);
    }

    @Override
    public void process() {
        Logger.Information(this, "RecordData", "Data recorded at " + this.getOccurrenceDate());
        this.from.getEngine().search(e -> e instanceof Client).forEach(Logger::Data);
        this.rescheduleAt(this.getOccurrenceDate().add(LogicalDuration.ofHours(1)));
    }
}
