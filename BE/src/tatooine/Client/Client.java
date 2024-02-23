package tatooine.Client;

import engine.SimEngine;
import engine.SimEntity;
import enstabretagne.base.logger.ToRecord;
import tatooine.Workshop.InitWorkshop;

import java.util.List;

@ToRecord(name = "Client")
public class Client extends SimEntity {
    public Client(SimEngine engine, InitClient ini) {
        super(engine, ini);
    }

    @Override
    protected void init() {
        super.init();
//        send(new GoToWorkshop(this.getEngine(),this));
    }

    @ToRecord(name = "AttributedWorkshops")
    public List<InitWorkshop.WorkshopType> getAttributedWorkshops() {
        return ((InitClient) this.getInitData()).workshops;
    }

    @ToRecord(name = "Name")
    public String getName() {
        return this.getInitData().getName();
    }
}
