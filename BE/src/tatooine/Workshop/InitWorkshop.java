package tatooine.Workshop;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.engine.InitData;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import utils.DateTimeFrenchFormat;

public class InitWorkshop extends InitData {
    private static final Jsonb jsonb = JsonbBuilder.create();

    public Frequenting frequenting;
    public int capacity;
    public LogicalDateTime opening;
    public LogicalDateTime closing;
    public int maxWorkshops = 1; // TODO: add it to class params.
    /**
     * The duration of the workshop
     * In minutes.
     * => maybe only put it as an integer and it will be converted to LogicalDuration in the Workshop class.
     */
    public LogicalDuration workshopDuration = LogicalDuration.ofMinutes(10); // TODO: add it to class params.
    public int efficiency = 1; // TODO: add it to class params.
    /**
     * The frequency of the failure
     * In days.
     */
    public LogicalDuration failureFrequency = LogicalDuration.ofDay(30); // TODO: add it to class params.
    /**
     * The standard deviation of the failure frequency
     * In days.
     */
    public int failureStandardDeviation = 1; // TODO: add it to class params.
    /**
     * The duration of the failure recovery
     * In days.
     */
    public LogicalDuration failureRecovery = LogicalDuration.ofDay(3); // TODO: add it to class params.
    public QueueType queueType = QueueType.ORGANIZED; // TODO: add it to class params.
    public int queueCapacity = 10; // TODO: add it to class params.

    public InitWorkshop(String name, int capacity, Frequenting frequenting, LogicalDateTime opening, LogicalDateTime closing) {
        super(name);
        this.capacity = capacity;
        this.frequenting = frequenting;
        this.opening = opening;
        this.closing = closing;
    }

    public InitWorkshop() {
        super("default workshop");
        this.capacity = 0;
        this.frequenting = Frequenting.FREE;
        this.opening = new LogicalDateTime(DateTimeFrenchFormat.of(1, 1, 2024, 7, 15));
        this.closing = new LogicalDateTime(DateTimeFrenchFormat.of(1, 1, 2024, 14, 0));
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
        InitWorkshop initWorkshop = new InitWorkshop("Workshop1", 10, Frequenting.FREE, new LogicalDateTime("01/01/2024 07:15:00.0000000"), new LogicalDateTime("01/01/2024 14:00:00.0000000"));
//        String json = jsonb.toJson(initWorkshop);
//        System.out.println(json);

//        System.out.println("Test if json correct");
//        InitWorkshop initWorkshop1 = fromJSON(json);
//        testInitWorkshopFromJson(initWorkshop, initWorkshop1);

        System.out.println("Test if json incorrect");
        String json2 = "{\"error\":\"Workshop1\",\"capacity\":10,\"frequenting\":\"FREE\"}";
        InitWorkshop initWorkshop2 = fromJSON(json2);
        testInitWorkshopFromJson(initWorkshop, initWorkshop2);

        System.out.println("Test if json correct");
        String json3 = "{\"name\":\"Workshop1\",\"capacity\":10,\"frequenting\":\"FREE\"}";
        InitWorkshop initWorkshop3 = fromJSON(json3);
        testInitWorkshopFromJson(initWorkshop, initWorkshop3);

//        System.out.println(("Test with a list of workshops"));
//        String json3 = "[" + json + "," + json + "]";
//        InitWorkshop[] initWorkshops = jsonb.fromJson(json3, InitWorkshop[].class);
//        System.out.println("results: " + Arrays.stream(initWorkshops).toList());
//        Arrays.stream(initWorkshops).toList().forEach(w -> testInitWorkshopFromJson(initWorkshop, w));
    }

    private static void testInitWorkshopFromJson(InitWorkshop defaultWorkshop, InitWorkshop testWorkshop) {
        System.out.println(testWorkshop);
        System.out.println(testWorkshop.name + ", " + testWorkshop.capacity + ", " + testWorkshop.frequenting);
        System.out.println(testWorkshop.name.equals(defaultWorkshop.name) && testWorkshop.capacity == defaultWorkshop.capacity && testWorkshop.frequenting == defaultWorkshop.frequenting);
    }

    public enum Frequenting {
        FREE, PLANNED
    }

    public enum QueueType {
        ORGANIZED, RANDOM
    }
}
