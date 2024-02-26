package tatooine.Events;

import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import tatooine.Workshop.Distances;
import tatooine.Workshop.InitWorkshop;
import tatooine.Workshop.InitWorkshop.QueueType;
import tatooine.Workshop.InitWorkshop.WorkshopType;
import tatooine.Workshop.Workshop;
import tatooine.Workshop.WorkshopTime;

import java.util.ArrayList;
import java.util.List;

public class CreateWorkshops extends SimEvent<SimEntity> {
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
        initWorkshops.add(new InitWorkshop("Terres chaudes", WorkshopType.TERRES, InitWorkshop.Frequenting.PLANNED, open_7_15, close_14, 6, 20, 20, 60, 10, 3, QueueType.ORGANIZED, 10));
        initWorkshops.add(new InitWorkshop("jets filiformes", WorkshopType.FILIFORMES, InitWorkshop.Frequenting.FREE, open_10, close_13, 4, 5, 30, 28, 4, 2, QueueType.RANDOM, 10));
        initWorkshops.add(new InitWorkshop("étuves", WorkshopType.ETUVE, InitWorkshop.Frequenting.PLANNED, open_7_15, close_14, 6, 15, 15, 21, 5, 3, QueueType.ORGANIZED, 6));
        initWorkshops.add(new InitWorkshop("bains à jets", WorkshopType.BAIN, InitWorkshop.Frequenting.FREE, open_7_15, close_14, 9, 20, 10, 35, 4, 2, QueueType.ORGANIZED, 15));
        initWorkshops.add(new InitWorkshop("douches", WorkshopType.DOUCHE, InitWorkshop.Frequenting.FREE, open_7_15, close_14, 8, 10, 10, 49, 2, 2, QueueType.RANDOM, 8));
        initWorkshops.add(new InitWorkshop("soins du visage", WorkshopType.VISAGE, InitWorkshop.Frequenting.FREE, open_7_15, close_14, 8, 10, 5, 365, 40, 1, QueueType.RANDOM, 5));
        initWorkshops.add(new InitWorkshop("Relaxation - détente", WorkshopType.RELAXATION, InitWorkshop.Frequenting.FREE, open_7_15, close_14, 999, 15, 0, 0, 0, 0, QueueType.RANDOM, 0));
        // ^ TODO: enhance the RELAXATION workshop.
        // TODO: add unused workshop.
        // TODO: add infinite workshop.

        // Distances between each workshops
        Distances.setWalkingDuration(WorkshopType.FILIFORMES, WorkshopType.DOUCHE, LogicalDuration.ofMinutes(1));
        Distances.setWalkingDuration(WorkshopType.FILIFORMES, WorkshopType.BAIN, LogicalDuration.ofMinutes(2));
        Distances.setWalkingDuration(WorkshopType.FILIFORMES, WorkshopType.VISAGE, LogicalDuration.ofMinutes(4));
        Distances.setWalkingDuration(WorkshopType.FILIFORMES, WorkshopType.ETUVE, LogicalDuration.ofMinutes(1));
        Distances.setWalkingDuration(WorkshopType.FILIFORMES, WorkshopType.TERRES, LogicalDuration.ofMinutes(3));

        Distances.setWalkingDuration(WorkshopType.DOUCHE, WorkshopType.BAIN, LogicalDuration.ofMinutes(1));
        Distances.setWalkingDuration(WorkshopType.DOUCHE, WorkshopType.VISAGE, LogicalDuration.ofMinutes(2));
        Distances.setWalkingDuration(WorkshopType.DOUCHE, WorkshopType.ETUVE, LogicalDuration.ofMinutes(2));
        Distances.setWalkingDuration(WorkshopType.DOUCHE, WorkshopType.TERRES, LogicalDuration.ofMinutes(4));

        Distances.setWalkingDuration(WorkshopType.BAIN, WorkshopType.VISAGE, LogicalDuration.ofMinutes(1));
        Distances.setWalkingDuration(WorkshopType.BAIN, WorkshopType.ETUVE, LogicalDuration.ofMinutes(3));
        Distances.setWalkingDuration(WorkshopType.BAIN, WorkshopType.TERRES, LogicalDuration.ofMinutes(3));

        Distances.setWalkingDuration(WorkshopType.VISAGE, WorkshopType.ETUVE, LogicalDuration.ofMinutes(4));
        Distances.setWalkingDuration(WorkshopType.VISAGE, WorkshopType.TERRES, LogicalDuration.ofMinutes(2));

        Distances.setWalkingDuration(WorkshopType.ETUVE, WorkshopType.TERRES, LogicalDuration.ofMinutes(2));

//        Logger.Data(new Distances());
        Distances.log();
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
