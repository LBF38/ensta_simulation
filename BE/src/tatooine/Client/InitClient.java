package tatooine.Client;

import engine.InitData;
import tatooine.Workshop.InitWorkshop;

import java.util.List;

public class InitClient extends InitData {
    public List<InitWorkshop.WorkshopType> workshops;

    public InitClient(String name, List<InitWorkshop.WorkshopType> workshops) {
        super(name);
        this.workshops = workshops;
    }
}
