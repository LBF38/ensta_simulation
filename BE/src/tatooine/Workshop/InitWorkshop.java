package tatooine.Workshop;

import enstabretagne.engine.InitData;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import java.util.Arrays;

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

    /**
     * Convert a json string to a InitWorkshop object
     * warning: this is a test for the moment. That's why it's private.
     *
     * @param json a json string
     * @return an InitWorkshop object
     */
    private static InitWorkshop fromJSON(String json) {
        return jsonb.fromJson(json, InitWorkshop.class);
    }

    public static void main(String[] args) {
        InitWorkshop initWorkshop = new InitWorkshop("Workshop1", 10, Frequenting.FREE);
        String json = jsonb.toJson(initWorkshop);
        System.out.println(json);

        System.out.println("Test if json correct");
        InitWorkshop initWorkshop1 = fromJSON(json);
        testInitWorkshopFromJson(initWorkshop, initWorkshop1);

        System.out.println("Test if json incorrect");
        String json2 = "{\"error\":\"Workshop1\",\"capacity\":10,\"frequenting\":\"FREE\"}";
        InitWorkshop initWorkshop2 = fromJSON(json2);
        testInitWorkshopFromJson(initWorkshop, initWorkshop2);

        System.out.println(("Test with a list of workshops"));
        String json3 = "[" + json + "," + json + "]";
        InitWorkshop[] initWorkshops = jsonb.fromJson(json3, InitWorkshop[].class);
        System.out.println("results: " + Arrays.stream(initWorkshops).toList());
        Arrays.stream(initWorkshops).toList().forEach(w -> testInitWorkshopFromJson(initWorkshop, w))
        ;
    }

    private static void testInitWorkshopFromJson(InitWorkshop defaultWorkshop, InitWorkshop testWorkshop) {
        System.out.println(testWorkshop);
        System.out.println(testWorkshop.name + ", " + testWorkshop.capacity + ", " + testWorkshop.frequenting);
        System.out.println(testWorkshop.name.equals(defaultWorkshop.name) && testWorkshop.capacity == defaultWorkshop.capacity && testWorkshop.frequenting == defaultWorkshop.frequenting);
    }

    public enum Frequenting {
        FREE, PLANNED
    }
}
