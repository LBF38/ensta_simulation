package utils;

import java.time.LocalDateTime;

public class DateTimeFrenchFormat {
    /**
     * Format inputs to a string formatted as "dd/MM/yyyy HH:mm:ss.nnnnnnn"
     *
     * @param day    the day
     * @param month  the month
     * @param year   the year
     * @param hour   the hour
     * @param minute the minute
     * @param second the second
     * @param nano   the nano
     * @return the string formatted as "dd/MM/yyyy HH:mm:ss.nnnnnnn"
     */
    public static String of(Integer day, Integer month, Integer year, Integer hour, Integer minute, Integer second, Integer nano) {
        return String.format("%02d/%02d/%04d %02d:%02d:%02d.%07d", day, month, year, hour, minute, second, nano);
    }

    public static String of(Integer day, Integer month, Integer year, Integer hour, Integer minute, Integer second) {
        return of(day, month, year, hour, minute, second, 0);
    }

    public static String of(Integer day, Integer month, Integer year, Integer hour, Integer minute) {
        return of(day, month, year, hour, minute, 0);
    }

    public static String of(Integer day, Integer month, Integer year, Integer hour) {
        return of(day, month, year, hour, 0);
    }

    public static String of(Integer day, Integer month, Integer year) {
        return of(day, month, year, 0);
    }

    public static String of(Integer day, Integer month) {
        return of(day, month, LocalDateTime.now().getYear());
    }

    public static String of(Integer day) {
        return of(day, 1);
    }


    public static void main(String[] args) {
        System.out.println(DateTimeFrenchFormat.of(1, 1, 2024, 7, 15, 0, 0));
        System.out.println(DateTimeFrenchFormat.of(1, 1, 2024, 7, 15, 0));
        System.out.println(DateTimeFrenchFormat.of(1, 1, 2024, 7, 15));
        System.out.println(DateTimeFrenchFormat.of(1, 1, 2024, 7));
        System.out.println(DateTimeFrenchFormat.of(1, 1, 2024));
        System.out.println(DateTimeFrenchFormat.of(1, 1));
        System.out.println(DateTimeFrenchFormat.of(1));
    }
}
