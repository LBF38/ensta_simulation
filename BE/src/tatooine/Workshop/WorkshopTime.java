package tatooine.Workshop;

import enstabretagne.base.time.LogicalDateTime;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.annotation.JsonbProperty;
import utils.DateTimeFrenchFormat;

/**
 * The time-dealing class of a workshop.
 */
public record WorkshopTime(int day, int month, int year, int hour,
                           int minute,
                           int second, int nanosecond) {
    private static final Jsonb jsonb = JsonbBuilder.create();

    public WorkshopTime() {
        this(1, 1, 2024, 0, 0, 0, 0);
    }

    public WorkshopTime(int day, int month, int year, int hour, int minute) {
        this(day, month, year, hour, minute, 0, 0);
    }

    public WorkshopTime(LogicalDateTime dateTime, int hour, int minute) {
        this(DateTimeFrenchFormat.toWorkshopTime(dateTime.toString()).day, DateTimeFrenchFormat.toWorkshopTime(dateTime.toString()).month, DateTimeFrenchFormat.toWorkshopTime(dateTime.toString()).year, hour, minute, 0, 0);
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
