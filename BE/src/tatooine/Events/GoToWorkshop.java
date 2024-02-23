package tatooine.Events;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import tatooine.Client.Client;
import tatooine.Workshop.InitWorkshop.WorkshopType;
import tatooine.Workshop.Workshop;

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
        this.from.getEngine().search(e -> e instanceof Workshop).forEach(entity ->
        {
            var workshop = ((Workshop) entity);
            if (workshop.getType() == this.workshop && workshop.canAddClient() && workshop.isOpen()) {
                workshop.addClient((Client) this.from);
                Logger.Information(this, "GoToWorkshop", "The client %s is added to the workshop %s's queue.".formatted(this.from.getName(), workshop.getType()));
                return;
            } else if (workshop.getType() == WorkshopType.RELAXATION) {
                // By default, the client goes to the relaxation workshop if the workshop is closed or full.
                Logger.Information(this, "GoToWorkshop", "The workshop %s is closed or full, the client %s goes to the relaxation workshop".formatted(this.workshop, this.from.getName()));
                workshop.addClient((Client) this.from);
                return;
                // FIXME: update this code as it doesn't work as intended.
            }
        });
    }
}
