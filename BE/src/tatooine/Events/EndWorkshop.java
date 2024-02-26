package tatooine.Events;

import engine.SimEvent;
import enstabretagne.base.time.LogicalDateTime;
import tatooine.Client.Client;
import tatooine.Workshop.Workshop;

/**
 * The event of a client leaving a workshop.
 */
public class EndWorkshop extends SimEvent<Client> {
    private final Workshop workshop;

    public EndWorkshop(LogicalDateTime occurrenceDate, Client client, Workshop workshop) {
        super(occurrenceDate, client, null);
        this.workshop = workshop;
    }

    @Override
    public void process() {
        // Client ends the workshop
        workshop.endWorkshop(this.from);
    }
}
