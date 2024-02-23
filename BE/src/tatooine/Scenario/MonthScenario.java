package tatooine.Scenario;

import engine.Scenario;
import engine.SimEngine;
import tatooine.Events.CreateClient;
import tatooine.Events.CreateWorkshops;

public class MonthScenario extends Scenario {
    public MonthScenario(SimEngine engine, InitMonthScenario init) {
        super(engine, init);
    }

    @Override
    public void createSimulatedEntities() {
        InitMonthScenario scenario = (InitMonthScenario) this.getInitData();
        int maxCapacity = scenario.maxInstituteCapacity;
        send(new CreateWorkshops(scenario.affluence.date(), this));
        for (int i = 0; i < scenario.affluence.maxRate() * maxCapacity; i++) {
            send(new CreateClient(scenario.affluence.date(), this));
        }
    }
}
