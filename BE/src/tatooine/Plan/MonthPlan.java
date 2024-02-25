package tatooine.Plan;

import engine.Plan;
import engine.Scenario;
import enstabretagne.base.time.LogicalDateTime;
import tatooine.Scenario.InitMonthScenario;
import tatooine.Scenario.MonthScenario;
import tatooine.Scenario.MonthlyAffluence;
import utils.DateTimeFrenchFormat;

import java.util.LinkedList;

public class MonthPlan extends Plan {
    LogicalDateTime start;
    LogicalDateTime end;
    LinkedList<Scenario> scenarios;

    public MonthPlan(int replicas, LogicalDateTime start, LogicalDateTime end) {
        super(replicas);
        scenarios = new LinkedList<>();
        this.start = start;
        this.end = end;
    }

    @Override
    public void initScenarii() {
        var affluence = new MonthlyAffluence(new LogicalDateTime(DateTimeFrenchFormat.of(1, 1, 2024)), 0.5);
        for (int i = 0; i < getReplicas(); i++) {
            scenarios.add(new MonthScenario(getEngine(), new InitMonthScenario("Month Scenario", start, end, i, 2, affluence, 2)));
        }
    }

    @Override
    public Scenario nextScenario() {
        if (!hasNextScenario()) {
            return null;
        }
        Scenario sc = scenarios.pop();

        engine.setCurrentScenario(sc);
        return sc;
    }

    @Override
    public boolean hasNextScenario() {
        return !scenarios.isEmpty();
    }
}
