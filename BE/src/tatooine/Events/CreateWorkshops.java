package tatooine.Events;

import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import tatooine.Workshop.InitWorkshop;
import tatooine.Workshop.Workshop;
import tatooine.Workshop.WorkshopTime;

import java.util.ArrayList;
import java.util.List;

public class CreateWorkshops extends SimEvent {
    public CreateWorkshops(LogicalDateTime occurrenceDate, SimEntity simEntity, SimEntity to) {
        super(occurrenceDate, simEntity, to);
    }

    public CreateWorkshops(LogicalDateTime occurrenceDate, SimEntity from) {
        super(occurrenceDate, from, null);
    }

    @Override
    public void process() {
        Logger.Information(this, "CreateWorkshops Event process", "Create workshops at " + this.getOccurrenceDate());
        List<InitWorkshop> initWorkshops = new ArrayList<>();
        var open_7_15 = new WorkshopTime(getOccurrenceDate(), 7, 15);
        var open_10 = new WorkshopTime(getOccurrenceDate(), 10, 0);
        var close_13 = new WorkshopTime(getOccurrenceDate(), 13, 0);
        var close_14 = new WorkshopTime(getOccurrenceDate(), 14, 0);
        //^ DONE: accept a LogicalDateTime for the date. The hour should be configurable.
        initWorkshops.add(new InitWorkshop("Terres chaudes", InitWorkshop.WorkshopType.TERRES, InitWorkshop.Frequenting.PLANNED, open_7_15, close_14, 6, 20, 20, 60, 10, 3, InitWorkshop.QueueType.ORGANIZED, 10));
        initWorkshops.add(new InitWorkshop("jets filiformes", InitWorkshop.WorkshopType.FILIFORMES, InitWorkshop.Frequenting.FREE, open_10, close_13, 4, 5, 30, 28, 4, 2, InitWorkshop.QueueType.RANDOM, 10));
        initWorkshops.add(new InitWorkshop("étuves", InitWorkshop.WorkshopType.ETUVE, InitWorkshop.Frequenting.PLANNED, open_7_15, close_14, 6, 15, 15, 21, 5, 3, InitWorkshop.QueueType.ORGANIZED, 6));
        initWorkshops.add(new InitWorkshop("bains à jets", InitWorkshop.WorkshopType.BAIN, InitWorkshop.Frequenting.FREE, open_7_15, close_14, 9, 20, 10, 35, 4, 2, InitWorkshop.QueueType.ORGANIZED, 15));
        initWorkshops.add(new InitWorkshop("douches", InitWorkshop.WorkshopType.DOUCHE, InitWorkshop.Frequenting.FREE, open_7_15, close_14, 8, 10, 10, 49, 2, 2, InitWorkshop.QueueType.RANDOM, 8));
        initWorkshops.add(new InitWorkshop("soins du visage", InitWorkshop.WorkshopType.VISAGE, InitWorkshop.Frequenting.FREE, open_7_15, close_14, 8, 10, 5, 365, 40, 1, InitWorkshop.QueueType.RANDOM, 5));
        // TODO: add unused workshop.
        // TODO: add infinite workshop.


        // TODO: idea - create the initWorkshops from a config file like:
        // var initWorkshops = InitWorkshop.fromFile("workshops.json");
        // Then, we will need to check if the config is valid and that objects are correctly created.
        // => will see that later.

        for (InitWorkshop initWorkshop : initWorkshops) {
            var workshop = new Workshop(this.from.getEngine(), initWorkshop);
            workshop.requestInit();
            Logger.Data(workshop);
        }
    }
}
