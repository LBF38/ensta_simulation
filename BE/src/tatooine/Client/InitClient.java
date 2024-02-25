package tatooine.Client;

import engine.InitData;
import enstabretagne.base.time.LogicalDuration;
import tatooine.Workshop.InitWorkshop;

import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class InitClient extends InitData {
    public Dictionary<InitWorkshop.WorkshopType, LogicalDuration> workshops;

    public InitClient(String name, Dictionary<InitWorkshop.WorkshopType, LogicalDuration> workshops) {
        super(name);
        this.workshops = workshops;
    }
}
