package tatooine.Plan;

import engine.Plan;
import engine.Scenario;
import enstabretagne.base.time.LogicalDateTime;
import tatooine.Scenario.*;

import java.util.LinkedList;
import java.util.Map;

public class YearPlan extends Plan {
    LogicalDateTime start;
    LogicalDateTime end;
    LinkedList<Scenario> scenarios;


    public YearPlan(int replicas, LogicalDateTime start, LogicalDateTime end) {
        super(replicas);
        scenarios = new LinkedList<>();
        this.start = start;
        this.end = end;
    }

    @Override
    public void initScenarii() {
        //open config file in ./config/institue.json


        Map<Integer, Double> monthlyAttendance =
                Map.of(3, 0.5,
                        4, 0.6,
                        5, 0.7,
                        6, 0.8,
                        7, 0.95,
                        8, 0.9,
                        9, 0.65);

        for (int i = 0; i < getReplicas(); i++) {
            scenarios.add(new YearScenario(getEngine(), new InitYearScenario("Year Scenario", start, end, i, 2, monthlyAttendance, 180)));
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
