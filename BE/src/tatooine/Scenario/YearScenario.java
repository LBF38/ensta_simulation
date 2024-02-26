package tatooine.Scenario;

import engine.Scenario;
import engine.SimEngine;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import tatooine.Client.InitClient;
import tatooine.Events.CreateClient;
import tatooine.Events.CreateWorkshops;
import tatooine.Events.RecordData;
import tatooine.Workshop.InitWorkshop.WorkshopType;
import utils.DateTimeFrenchFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A scenario that lasts a year, taking into account different months, and a changing clients set.
 */
public class YearScenario extends Scenario {
    public YearScenario(SimEngine engine, InitYearScenario init) {
        super(engine, init);
    }

    @Override
    public void createSimulatedEntities() {
        InitYearScenario scenario = (InitYearScenario) this.getInitData();
        int maxCapacity = scenario.maxInstituteCapacity;
        LogicalDateTime startDate = new LogicalDateTime(DateTimeFrenchFormat.of(1, 3, 2024));
        send(new CreateWorkshops(startDate, this));
        LogicalDateTime currentDate = startDate;
        List<Double> mondayArrivals = Affluence.generateMondaysArrivals(maxCapacity, scenario.monthlyAttendance);
        int week = 0;
        while (currentDate.getMonthValue() < 10) {
            int monthCapacity = (int) (scenario.monthlyAttendance.get(currentDate.getMonthValue()) * maxCapacity);
            for (int i = 0; i < mondayArrivals.get(week); i++) {
                var initClient = new InitClient("Client %d%d".formatted(week, i), attributedWorkshops(i, monthCapacity));
                send(new CreateClient(currentDate, this, initClient));
            }
            currentDate = currentDate.add(LogicalDuration.ofDay(7));
            week++;
        }
    }

    @Override
    protected void init() {
        super.init();
        send(new RecordData(now().truncateToDays().add(LogicalDuration.ofHours(7).add(LogicalDuration.ofMinutes(15))), this));
    }

    private List<WorkshopType> attributedWorkshops(int i, int maxCapacity) {
//        var rates = List.of(0.2, 0.35, 0.3, 0.15);
        var rates = List.of(0.2, 0.55, 0.85, 1.0);
        for (int k = 0; k < rates.size(); k++) {
            if (i < rates.get(k) * maxCapacity && (k > 0 && i >= rates.get(k - 1) * maxCapacity)) {
                return generateRandomAttributedWorkshops(k + 3);
            }
        }
        return generateRandomAttributedWorkshops(3); // default value
    }

    private List<WorkshopType> generateRandomAttributedWorkshops(int maxWorkshops) {
        var workshopsTypes = Arrays.stream(WorkshopType.values()).filter(w -> w != WorkshopType.HOME && w != WorkshopType.RELAXATION).toList();
        List<WorkshopType> workshops = new ArrayList<>();
        for (int j = 0; j < maxWorkshops; j++) {
            int randomIndex = this.getEngine().getRandomGenerator().nextInt(0, workshopsTypes.size());
            workshops.add(workshopsTypes.get(randomIndex));
        }
        return workshops;
    }
}
