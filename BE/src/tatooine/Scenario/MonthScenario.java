package tatooine.Scenario;

import engine.Scenario;
import engine.SimEngine;
import enstabretagne.base.time.LogicalDuration;
import tatooine.Client.InitClient;
import tatooine.Events.CreateClient;
import tatooine.Events.CreateWorkshops;
import tatooine.Events.RecordData;
import tatooine.Workshop.InitWorkshop.WorkshopType;

import java.util.*;

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
        send(new RecordData(now().truncateToDays().add(LogicalDuration.ofHours(7).add(LogicalDuration.ofMinutes(15))), this));
    }

    private Dictionary<WorkshopType, Integer> attributedWorkshops(int i, int maxCapacity) {
//        var rates = List.of(0.2, 0.35, 0.3, 0.15);
        var rates = List.of(0.2, 0.55, 0.85, 1.0);
        for (int k = 0; k < rates.size(); k++) {
            if (i < rates.get(k) * maxCapacity && (k > 0 && i >= rates.get(k - 1) * maxCapacity)) {
                return generateRandomAttributedWorkshops(k + 3);
            }
        }
        return generateRandomAttributedWorkshops(3); // default value
    }

    private Dictionary<WorkshopType, Integer> generateRandomAttributedWorkshops(int maxWorkshops) {
        var workshopsTypes = Arrays.stream(WorkshopType.values()).filter(w -> w != WorkshopType.HOME && w != WorkshopType.RELAXATION).toList();
        Dictionary<WorkshopType, Integer> workshops = new Hashtable<>();
        for (int j = 0; j < maxWorkshops; j++) {
            int randomIndex = this.getEngine().getRandomGenerator().nextInt(0, workshopsTypes.size());
            // NB : The center can handle 180 clients per day.
            // All workshops together can handle 41 clients simultaneously (unused workshop not accounted).
            // So, to host 180 clients and maximize the rentability, one client can get 180/41 = 22% of the day duration.
            // The center is opened from 7:15 to 14:00, so 405 minutes a day.
            // So each client of one day can get 405 * 0.22 = 89.1 minutes.
            // Roughly 90 minutes.
            // We choose that one client tries to get a uniform distribution among its workshops.
            workshops.put(workshopsTypes.get(randomIndex), 90/maxWorkshops);
        }
        return workshops;
    }
}
