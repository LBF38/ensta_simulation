package tatooine.Workshop;

import engine.InitData;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

/**
 * The initial data of a workshop.
 */
public class InitWorkshop extends InitData {
    /**
     * The json builder, in order to be able to instantiate a workshop reading a json file.
     */
    private static final Jsonb jsonb = JsonbBuilder.create();
    public final WorkshopType type;
    /**
     * The type of schedule that the workshop tolerates
     */
    public Frequenting frequenting;
    /**
     * The maximum number of clients that the workshop can handle simultaneously.
     */
    public int capacity;
    /**
     * The opening and closing time of the workshop
     */
    public WorkshopTime opening;
    public WorkshopTime closing;
    /**
     * The duration of the workshop
     * In minutes.
     * => maybe only put it as an integer and it will be converted to LogicalDuration in the Workshop class.
     */
    public int duration;
    public int efficiency;
    /**
     * The frequency of the failure
     * In days.
     */
    public int failureFrequency;
    /**
     * The standard deviation of the failure frequency
     * In days.
     */
    public int failureStandardDeviation;
    /**
     * The duration of the failure recovery
     * In days.
     */
    public int failureRecovery;
    public QueueType queueType;
    public int queueCapacity;

    public InitWorkshop(String name, WorkshopType type, Frequenting frequenting, WorkshopTime opening, WorkshopTime closing, int capacity, int duration, int efficiency, int failureFrequency, int failureStandardDeviation, int failureRecovery, QueueType queueType, int queueCapacity) {
        super(name);
        this.type = type;
        this.capacity = capacity;
        this.frequenting = frequenting;
        this.opening = opening;
        this.closing = closing;
        this.duration = duration;
        this.efficiency = efficiency;
        this.failureFrequency = failureFrequency;
        this.failureStandardDeviation = failureStandardDeviation;
        this.failureRecovery = failureRecovery;
        this.queueType = queueType;
        this.queueCapacity = queueCapacity;
    }

    public InitWorkshop() {
        super("default workshop");
        this.type = WorkshopType.TERRES;
        this.capacity = 0;
        this.frequenting = Frequenting.FREE;
        this.opening = new WorkshopTime(1, 1, 2024, 7, 15, 0, 0);
        this.closing = new WorkshopTime(1, 1, 2024, 14, 0, 0, 0);
        this.duration = 10;
        this.efficiency = 1;
        this.failureFrequency = 30;
        this.failureStandardDeviation = 1;
        this.failureRecovery = 3;
        this.queueType = QueueType.ORGANIZED;
        this.queueCapacity = 10;
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
        InitWorkshop initWorkshop = new InitWorkshop("Workshop1", WorkshopType.TERRES, Frequenting.FREE, new WorkshopTime(5, 2, 2024, 8, 25), new WorkshopTime(5, 2, 2024, 15, 0), 10, 25, 5, 40, 2, 5, QueueType.RANDOM, 20);
        String json = jsonb.toJson(initWorkshop);
        System.out.println(json);

        System.out.println("Test if json correct");
        InitWorkshop initWorkshop1 = fromJSON(json);
        testInitWorkshopFromJson(initWorkshop, initWorkshop1);
        System.out.println(jsonb.toJson(initWorkshop1));
        // FIXME: make the InitWorkshop class serializable
        // => the objective is to be able to create an InitWorkshop object from a json string

//        System.out.println("Test if json incorrect");
//        String json2 = "{\"error\":\"Workshop1\",\"capacity\":10,\"frequenting\":\"FREE\"}";
//        InitWorkshop initWorkshop2 = fromJSON(json2);
//        testInitWorkshopFromJson(initWorkshop, initWorkshop2);
//
//        System.out.println("Test if json correct");
//        String json3 = "{\"name\":\"Workshop1\",\"capacity\":10,\"frequenting\":\"FREE\"}";
//        InitWorkshop initWorkshop3 = fromJSON(json3);
//        testInitWorkshopFromJson(initWorkshop, initWorkshop3);

//        System.out.println(("Test with a list of workshops"));
//        String json3 = "[" + json + "," + json + "]";
//        InitWorkshop[] initWorkshops = jsonb.fromJson(json3, InitWorkshop[].class);
//        System.out.println("results: " + Arrays.stream(initWorkshops).toList());
//        Arrays.stream(initWorkshops).toList().forEach(w -> testInitWorkshopFromJson(initWorkshop, w));
    }

    private static void testInitWorkshopFromJson(InitWorkshop defaultWorkshop, InitWorkshop testWorkshop) {
        System.out.println(testWorkshop);
        System.out.println("Test name: " + (defaultWorkshop.getName().equals(testWorkshop.getName()) ? "OK" : "KO"));
    }

    public enum WorkshopType {
        HOME, RELAXATION, TERRES, FILIFORMES, ETUVE, BAIN, DOUCHE, VISAGE
    }

    public enum Frequenting {
        FREE, PLANNED
    }

    public enum QueueType {
        ORGANIZED, RANDOM
    }
}
