package tatooine.Events;

import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import tatooine.Client.Client;
import tatooine.Workshop.InitWorkshop;
import tatooine.Workshop.Workshop;
import engine.SimEngine;

import java.util.Collections;
import java.util.List;

public class ClientLeavingDaily extends SimEvent<Client> {
    public ClientLeavingDaily(LogicalDateTime occurrenceDate, Client from) {
        super(occurrenceDate, from, null);
    }

    @Override
    public void process() {
        Logger.Information(this, "ClientLeavingDaily",  this.from + " left center at " + this.getOccurrenceDate());
        List<InitWorkshop.WorkshopType> remainingWorkshops = this.from.WorkshopsNotDone();
        remainingWorkshops.forEach(workshopType -> {
            Workshop workshop = (Workshop) this.from.getEngine().search(e -> e instanceof Workshop && ((Workshop) e).getType() == workshopType).getFirst();
            workshop.increaseDailyPerfectEfficiency();
        });
        this.from.resetWorkshops();
        // Planning the arrival of the client the next day, 7:15.
        LogicalDateTime nextArrival = this.from.now().truncateToDays().add(LogicalDuration.ofDay(1).add(LogicalDuration.ofHours(7)).add(LogicalDuration.ofMinutes(15)));
        remainingWorkshops = this.from.WorkshopsNotDone();
        var nextWorkshopType = remainingWorkshops.get(this.from.getEngine().getRandomGenerator().nextInt(remainingWorkshops.size()));
        this.from.send(new GoToWorkshop(nextArrival, this.from, nextWorkshopType));
    }
}
