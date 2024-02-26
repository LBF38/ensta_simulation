package tatooine.Scenario;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import utils.DateTimeFrenchFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The class that generates the base affluence of the institute on Mondays : the day of new Clients arrival.
 */
public class Affluence {
    private static final List<Double> mondaysArrivals = new ArrayList<>();

    /**
     * Generates the affluence of the institute for Mondays.
     * @param maxCapacity The maximum capacity of the institute.
     * @param monthlyAttendance The monthly attendance of the institute.
     * @return The affluence of the institute on Mondays.
     */
    public static List<Double> generateMondaysArrivals(int maxCapacity, Map<Integer, Double> monthlyAttendance) {
        LogicalDateTime currentDate = new LogicalDateTime(DateTimeFrenchFormat.of(1, 3, 2024));
        while (currentDate.getDayOfWeek().getValue() != 1) { //Find first Monday of the month
            currentDate = currentDate.add(LogicalDuration.ofDay(1));
        }
        Double currentAffluence = (double) 0;
        int week = 0;
        while (currentDate.getMonthValue() < 10) {
            if (week == 1) {
                currentAffluence = mondaysArrivals.get(0);
            } else if (week > 1) {
                currentAffluence = mondaysArrivals.get(week - 1) + mondaysArrivals.get(week - 2);
            }

            double monday_arrivals = maxCapacity * monthlyAttendance.get(currentDate.getMonthValue()) - currentAffluence;
            if (monday_arrivals < 0) {
                monday_arrivals = 0.0;
            }
            mondaysArrivals.add(monday_arrivals);
            currentDate = currentDate.add(LogicalDuration.ofDay(7));
            week++;
        }
        return mondaysArrivals;
    }
}

