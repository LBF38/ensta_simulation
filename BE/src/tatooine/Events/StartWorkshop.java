package tatooine.Events;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import tatooine.Client.Client;
import tatooine.Workshop.Workshop;

public class StartWorkshop extends SimEvent<Client> {
    private final Workshop workshop;

    public StartWorkshop(LogicalDateTime occurrenceDate, Client client, Workshop workshop) {
        super(occurrenceDate, client, null);
        this.workshop = workshop;
    }

    @Override
    public void process() {
        // Client starts the workshop
        Logger.Detail(this, "StartWorkshop", "Client %s starts the workshop %s at %s".formatted(this.from.getName(), this.workshop.getType(), this.getOccurrenceDate()));
        workshop.startWorkshop(this.from);
    }
}
