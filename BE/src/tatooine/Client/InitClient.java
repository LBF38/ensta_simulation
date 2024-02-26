package tatooine.Client;

import engine.InitData;
import tatooine.Workshop.InitWorkshop;

import java.util.List;

/**
 * This class represents the initial data for a client. I.E. a name and a set of workshop types to visit each day.
 */
public class InitClient extends InitData {
    public List<InitWorkshop.WorkshopType> workshops;

    public InitClient(String name, List<InitWorkshop.WorkshopType> workshops) {
        super(name);
        this.workshops = workshops;
    }
}
