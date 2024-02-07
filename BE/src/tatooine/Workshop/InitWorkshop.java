package tatooine.Workshop;

import enstabretagne.engine.InitData;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public class InitWorkshop extends InitData {
    private static final Jsonb jsonb = JsonbBuilder.create();

    public Frequenting frequenting;
    public int capacity;

    public InitWorkshop(String name, int capacity, Frequenting frequenting) {
        super(name);
        this.capacity = capacity;
        this.frequenting = frequenting;
    }

    public InitWorkshop() {
        super("default workshop");
        this.capacity = 0;
        this.frequenting = Frequenting.FREE;
    }

    public static InitWorkshop fromJSON(String json) {
        return jsonb.fromJson(json, InitWorkshop.class);
    }

    public static void main(String[] args) {
        InitWorkshop initWorkshop = new InitWorkshop("Workshop1", 10, Frequenting.FREE);
        String json = jsonb.toJson(initWorkshop);
        System.out.println(json);
        InitWorkshop initWorkshop2 = fromJSON(json);
        System.out.println(initWorkshop2);
        System.out.println(initWorkshop2.name + ", " + initWorkshop2.capacity + ", " + initWorkshop2.frequenting);
        System.out.println(initWorkshop2.name.equals(initWorkshop.name) && initWorkshop2.capacity == initWorkshop.capacity && initWorkshop2.frequenting == initWorkshop.frequenting);
    }

    public enum Frequenting {
        FREE, PLANNED
    }
}
