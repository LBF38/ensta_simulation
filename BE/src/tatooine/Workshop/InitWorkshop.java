package tatooine.Workshop;

import engine.InitData;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.annotation.JsonbProperty;
import utils.DateTimeFrenchFormat;

public class InitWorkshop extends InitData {
    private static final Jsonb jsonb = JsonbBuilder.create();

    public Frequenting frequenting;
    public int capacity;
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

    public InitWorkshop(String name, int capacity, Frequenting frequenting, WorkshopTime opening, WorkshopTime closing, int duration, int efficiency, int failureFrequency, int failureStandardDeviation, int failureRecovery, QueueType queueType, int queueCapacity) {
        super(name);
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
        InitWorkshop initWorkshop = new InitWorkshop("Workshop1", 10, Frequenting.FREE, new WorkshopTime(5, 2, 2024, 8, 25, 0, 0), new WorkshopTime(5, 2, 2024, 15, 0, 0, 0), 25, 5, 40, 2, 5, QueueType.RANDOM, 20);
        String json = jsonb.toJson(initWorkshop);
        System.out.println(json);

        System.out.println("Test if json correct");
        InitWorkshop initWorkshop1 = fromJSON(json);
        testInitWorkshopFromJson(initWorkshop, initWorkshop1);

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

    public enum Frequenting {
        FREE, PLANNED
    }

    public enum QueueType {
        ORGANIZED, RANDOM
    }

    public record WorkshopTime(int day, int month, int year, int hour,
                               int minute,
                               int second, int nanosecond) {
        public WorkshopTime() {
            this(1, 1, 2024, 0, 0, 0, 0);
        }

        public static WorkshopTime fromJson(String json) {
            return jsonb.fromJson(json, WorkshopTime.class);
        }

        public String toJson() {
            return jsonb.toJson(this);
        }

        @Override
        public String toString() {
            return "WorkshopTime{" + "day=" + day + ", month=" + month + ", year=" + year + ", hour=" + hour + ", minute=" + minute + ", second=" + second + ", nanosecond=" + nanosecond + '}';
        }

        public String toDateTimeFrenchFormat() {
            return DateTimeFrenchFormat.of(day, month, year, hour, minute, second, nanosecond);
        }

        @JsonbProperty("day")
        public int getDay() {
            return day;
        }

        @JsonbProperty("month")
        public int getMonth() {
            return month;
        }

        @JsonbProperty("year")
        public int getYear() {
            return year;
        }

        @JsonbProperty("hour")
        public int getHour() {
            return hour;
        }

        @JsonbProperty("minute")
        public int getMinute() {
            return minute;
        }

        @JsonbProperty("second")
        public int getSecond() {
            return second;
        }

        @JsonbProperty("nanosecond")
        public int getNanosecond() {
            return nanosecond;
        }
    }
}
