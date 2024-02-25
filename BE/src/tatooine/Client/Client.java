package tatooine.Client;

import engine.SimEngine;
import engine.SimEntity;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import tatooine.Events.GoToWorkshop;
import tatooine.Workshop.Distances;
import tatooine.Workshop.InitWorkshop;
import tatooine.Workshop.InitWorkshop.WorkshopType;

import java.util.Dictionary;
import java.util.List;
import java.util.*;

@ToRecord(name = "Client")
public class Client extends SimEntity {
    /**
     * Data showing which workshops the client has already done, so far.
     */
    private Dictionary<InitWorkshop.WorkshopType, Boolean> dailyWorkshops = new Hashtable<>();

    /**
     * The time at which the client has started his workshop (initialized at the moment of creation).
     */
    private LogicalDateTime workshop_starting_time = this.now();
    public Client(SimEngine engine, InitClient ini) {
        super(engine, ini);
    }

    @Override
    protected void init() {
        super.init();
        List<WorkshopType> demandedWorkshops = Collections.list(this.getAttributedWorkshops().keys());
        var nextWorkshopType = demandedWorkshops.get(this.getEngine().getRandomGenerator().nextInt(demandedWorkshops.size()));
        var clientArrival = now().add(LogicalDuration.ofHours(7).add(LogicalDuration.ofMinutes(15)));
        var walking = clientArrival.add(Distances.getWalkingDuration(WorkshopType.HOME, nextWorkshopType));
        for (InitWorkshop.WorkshopType demandedWorkshop : demandedWorkshops) {
            this.dailyWorkshops.put(demandedWorkshop, false);
        }
        send(new GoToWorkshop(walking, this, nextWorkshopType));
    }

    @ToRecord(name = "AttributedWorkshops")
    public Dictionary<WorkshopType, LogicalDuration> getAttributedWorkshops() {
        return ((InitClient) this.getInitData()).workshops;
    }

    @ToRecord(name = "Name")
    public String getName() {
        return this.getInitData().getName();
    }

    public void setWorkshopStartingTime(LogicalDateTime workshop_starting_time) {
        this.workshop_starting_time = workshop_starting_time;
    }

    @ToRecord(name = "WorkshopStartingTime")
    public LogicalDateTime getWorkshopStartingTime() {
        return this.workshop_starting_time;
    }

    /**
     * Mark the workshop as done.
     * @param workshopType the type of the workshop to mark as done.
     */
    public void markWorkshopDone(WorkshopType workshopType) {

        if (workshopType != WorkshopType.RELAXATION && workshopType != WorkshopType.HOME) {
            this.dailyWorkshops.put(workshopType, true);
        }
    }

    /**
     * Check if the workshop is done.
     * @param workshopType the type of the workshop to check.
     * @return true if the workshop is done, false otherwise.
     */
    public boolean isWorkshopDone(WorkshopType workshopType) {
        return this.dailyWorkshops.get(workshopType);
    }

    /**
     * Get the workshops not done during the day.
     * @return the workshops not done.
     */
    public List<WorkshopType> WorkshopsNotDone() {
        List<WorkshopType> demandedWorkshops = Collections.list(this.getAttributedWorkshops().keys());
        List<WorkshopType> workshopsNotDone = new ArrayList<>();
        for (InitWorkshop.WorkshopType demandedWorkshop : demandedWorkshops) {
            if (!this.dailyWorkshops.get(demandedWorkshop)) {
                workshopsNotDone.add(demandedWorkshop);
            }
        }
        return workshopsNotDone;
    }

    /**
     * Reset the workshops done.
     */
    public void resetWorkshops() {

        this.dailyWorkshops = new Hashtable<>();
        List<WorkshopType> demandedWorkshops = Collections.list(this.getAttributedWorkshops().keys());
        for (InitWorkshop.WorkshopType demandedWorkshop : demandedWorkshops) {
            this.dailyWorkshops.put(demandedWorkshop, false);
        }
    }

    public void leaveCurationCenterForDay() {

    }
}
