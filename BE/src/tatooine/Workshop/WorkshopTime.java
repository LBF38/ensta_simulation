package tatooine.Workshop;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.annotation.JsonbProperty;
import utils.DateTimeFrenchFormat;

public record WorkshopTime(int day, int month, int year, int hour,
                           int minute,
                           int second, int nanosecond) {
    private static final Jsonb jsonb = JsonbBuilder.create();

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
