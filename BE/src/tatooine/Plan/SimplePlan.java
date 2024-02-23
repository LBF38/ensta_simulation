package tatooine.Plan;

import engine.Plan;
import engine.Scenario;
import enstabretagne.base.time.LogicalDateTime;
import tatooine.Scenario.InitScenario;
import tatooine.Scenario.SimpleScenario;

import java.util.LinkedList;

public class SimplePlan extends Plan {

    LogicalDateTime start;
    LogicalDateTime end;
    LinkedList<Scenario> scenarios;

    public SimplePlan(int replicas, LogicalDateTime start, LogicalDateTime end) {
        super(replicas);
        scenarios = new LinkedList<>();
        this.start = start;
        this.end = end;
    }

    @Override
    public void initScenarii() {
        for (int i = 0; i < getReplicas(); i++)
            scenarios.add(
                    new SimpleScenario(getEngine(), new InitScenario("Simple scenario", i, 5, start, end, 2)));
    }

    @Override
    public Scenario nextScenario() {
        if (hasNextScenario()) {
            Scenario sc = scenarios.pop();

            engine.setCurrentScenario(sc);
            return sc;
        }
        return null;
    }

    @Override
    public boolean hasNextScenario() {
        return !scenarios.isEmpty();
    }

}
