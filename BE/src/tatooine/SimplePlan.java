package tatooine;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.engine.Plan;
import enstabretagne.engine.Scenario;
import tatooine.Scenario.InitScenario;
import tatooine.Scenario.SimpleScenario;

import java.util.LinkedList;
public class SimplePlan extends Plan {

    LogicalDateTime start;
    LogicalDateTime end;

    public SimplePlan(int nbReplique, LogicalDateTime start, LogicalDateTime end) {
        super(nbReplique);
        scenarios = new LinkedList<>();
        this.start = start;
        this.end = end;
    }
    LinkedList<Scenario> scenarios;
    @Override
    public void initScenarii() {
        for (int i = 0; i < getNbReplique(); i++)
            scenarios.add(
                    new SimpleScenario(getEngine(), new InitScenario("Simple scenario", i, start, end, 2)));
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
