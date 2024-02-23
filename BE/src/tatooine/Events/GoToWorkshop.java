package tatooine.Events;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import tatooine.Client.Client;
import tatooine.Workshop.InitWorkshop.WorkshopType;

public class GoToWorkshop extends SimEvent {
    private final WorkshopType workshop;

    public GoToWorkshop(LogicalDateTime occurrenceDate, Client client, WorkshopType workshop) {
        super(occurrenceDate, client, null);
        this.workshop = workshop;
    }

    @Override
    public void process() {
        Logger.Information(this, "GoToWorkshop", "Client %s goes to the workshop %s at %s".formatted(this.from.getName(), this.workshop, this.getOccurrenceDate()));
        // TODO: add logic to add the client to workshop's queue
    }
}
