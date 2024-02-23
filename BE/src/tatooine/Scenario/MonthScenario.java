package tatooine.Scenario;

import engine.Scenario;
import engine.SimEngine;
import tatooine.Client.InitClient;
import tatooine.Events.CreateClient;
import tatooine.Events.CreateWorkshops;
import tatooine.Events.RecordData;
import tatooine.Workshop.InitWorkshop;

import java.util.ArrayList;
import java.util.List;

public class MonthScenario extends Scenario {
    public MonthScenario(SimEngine engine, InitMonthScenario init) {
        super(engine, init);
    }

    @Override
    public void createSimulatedEntities() {
        InitMonthScenario scenario = (InitMonthScenario) this.getInitData();
        int maxCapacity = scenario.maxInstituteCapacity;
        int monthCapacity = (int) (scenario.affluence.maxRate() * maxCapacity);
        send(new CreateWorkshops(scenario.affluence.date(), this));
        for (int i = 0; i < monthCapacity; i++) {
            var initClient = new InitClient("Client %d".formatted(i), attributedWorkshops(i, monthCapacity));
            send(new CreateClient(scenario.affluence.date(), this, initClient));
        }
    }

    @Override
    protected void init() {
        super.init();
        send(new RecordData(now(), this));
    }

    private List<InitWorkshop.WorkshopType> attributedWorkshops(int i, int maxCapacity) {
//        var rates = List.of(0.2, 0.35, 0.3, 0.15);
        var rates = List.of(0.2, 0.55, 0.85, 1.0);
        for (int k = 0; k < rates.size(); k++) {
            if (i < rates.get(k) * maxCapacity && (k > 0 && i >= rates.get(k - 1) * maxCapacity)) {
                return generateRandomAttributedWorkshops(k + 3);
            }
        }
        return generateRandomAttributedWorkshops(3); // default value
    }

    private List<InitWorkshop.WorkshopType> generateRandomAttributedWorkshops(int maxWorkshops) {
        var workshopsTypes = InitWorkshop.WorkshopType.values();
        List<InitWorkshop.WorkshopType> workshops = new ArrayList<>();
        for (int j = 0; j < maxWorkshops; j++) {
            int randomIndex = this.getEngine().getRandomGenerator().nextInt(0, workshopsTypes.length);
            workshops.add(workshopsTypes[randomIndex]);
        }
        return workshops;
    }
}
