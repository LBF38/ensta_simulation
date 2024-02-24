package tatooine.Client;

import engine.InitData;
import tatooine.Workshop.InitWorkshop;

import java.util.Dictionary;
import java.util.List;

public class InitClient extends InitData {
    public Dictionary<InitWorkshop.WorkshopType, Integer> workshops;

    public InitClient(String name, Dictionary<InitWorkshop.WorkshopType, Integer> workshops) {
        super(name);
        this.workshops = workshops;
    }
}
