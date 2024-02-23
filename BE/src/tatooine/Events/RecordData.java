package tatooine.Events;

import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import tatooine.Client.Client;
import tatooine.Workshop.Workshop;
import tatooine.Workshop.WorkshopTime;

public class RecordData extends SimEvent {
    public RecordData(LogicalDateTime occurrenceDate, SimEntity simEntity) {
        super(occurrenceDate, simEntity, null);
    }

    @Override
    public void process() {
        Logger.Information(this, "RecordData", "Data recorded at " + this.getOccurrenceDate());
        this.from.getEngine().search(e -> e instanceof Client).forEach(Logger::Data);
        this.from.getEngine().search(e -> e instanceof Workshop).forEach(Logger::Data);
//        var open_7_15 = new WorkshopTime(getOccurrenceDate(), 7, 15);
        var close_14 = new WorkshopTime(getOccurrenceDate(), 14, 0);
        if (this.getOccurrenceDate().compareTo(new LogicalDateTime(close_14.toDateTimeFrenchFormat())) >= 0) {
            var newDate = this.getOccurrenceDate().add(LogicalDuration.ofDay(1)).truncateToDays().add(LogicalDuration.ofHours(7).add(LogicalDuration.ofMinutes(0)));
            this.rescheduleAt(newDate);
            return;
        }
        this.rescheduleAt(this.getOccurrenceDate().add(LogicalDuration.ofMinutes(30)));
    }
}
