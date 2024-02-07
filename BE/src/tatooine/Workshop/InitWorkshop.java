package tatooine.Workshop;

import enstabretagne.engine.InitData;

public class InitWorkshop extends InitData {

    public Frequenting frequenting;
    public int capacity;

    public InitWorkshop(String name, int capacity, Frequenting frequenting) {
        super(name);
        this.capacity = capacity;
        this.frequenting = frequenting;
    }

    public enum Frequenting {
        FREE, PLANNED
    }

}
