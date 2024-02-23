package tatooine.Client;

import engine.SimEngine;
import engine.SimEntity;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.base.time.LogicalDuration;
import tatooine.Events.GoToWorkshop;
import tatooine.Workshop.Distances;
import tatooine.Workshop.InitWorkshop.WorkshopType;

import java.util.List;

@ToRecord(name = "Client")
public class Client extends SimEntity {
    public Client(SimEngine engine, InitClient ini) {
        super(engine, ini);
    }

    @Override
    protected void init() {
        super.init();
        var workshop = getAttributedWorkshops().remove(0);
        var clientArrival = now().add(LogicalDuration.ofHours(7).add(LogicalDuration.ofMinutes(15)));
        var walking = clientArrival.add(Distances.getWalkingDuration(WorkshopType.HOME, workshop));
        send(new GoToWorkshop(walking, this, workshop));
    }

    @ToRecord(name = "AttributedWorkshops")
    public List<WorkshopType> getAttributedWorkshops() {
        return ((InitClient) this.getInitData()).workshops;
    }

    @ToRecord(name = "Name")
    public String getName() {
        return this.getInitData().getName();
    }
}
