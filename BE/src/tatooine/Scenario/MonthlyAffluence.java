package tatooine.Scenario;

import enstabretagne.base.time.LogicalDateTime;

public record MonthlyAffluence(LogicalDateTime date, double maxRate) {
    @Override
    public String toString() {
        return "MonthlyAffluence{" + "date=" + date + ", maxRate=" + maxRate + '}';
    }
}
