package tatooine.Client;

import enstabretagne.engine.InitData;
import tatooine.Workshop.Workshop;

import java.util.List;

public class InitClient extends InitData {

    public List<Workshop> workshop_plan;

    public InitClient(String name, List<Workshop> workshop_plan) {
        super(name);
        this.workshop_plan = workshop_plan;
    }

}
