package tatooine.Scenario;

import engine.Scenario;
import engine.SimEngine;
import tatooine.Events.CreateClient;

public class MonthScenario extends Scenario {
    public MonthScenario(SimEngine engine, InitMonthScenario init) {
        super(engine, init);
    }

    @Override
    public void createSimulatedEntities() {
        InitMonthScenario scenario = (InitMonthScenario) this.getInitData();
        int maxCapacity = scenario.maxInstituteCapacity;
        for (int i = 0; i < scenario.affluence.maxRate() * maxCapacity; i++) {
            send(new CreateClient(scenario.affluence.date(), this));
        }
    }
}
