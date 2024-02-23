package engine;


import enstabretagne.base.logger.Logger;
import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.basics.IScenarioIdProvider;
import enstabretagne.simulation.basics.ISimulationDateProvider;
import enstabretagne.simulation.basics.ScenarioId;
import enstabretagne.simulation.basics.SortedList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SimEngine implements ISimulationDateProvider, IScenarioIdProvider {
    private final SortedList<SimEvent> scheduler = new SortedList<>();
    protected List<SimEntity> simulatedEntities = new ArrayList<>();
    Scenario currentScenario;
    private LogicalDateTime start;
    private LogicalDateTime end;
    private LogicalDateTime current;
    private MoreRandom randomGenerator;

    public SimEngine() {
        // Set logger
        Logger.setDateProvider(this);
        Logger.setScenarioIdProvider(this);
    }

    public MoreRandom getRandomGenerator() {
        return randomGenerator;
    }

    protected LogicalDateTime getCurrentDate() {
        return current;
    }

    public Scenario getCurrentScenario() {
        return currentScenario;
    }

    /**
     * Change le scénario en cours d'exécution.
     *
     * @param sc scénario à exécuter
     */
    public void setCurrentScenario(Scenario sc) {
        currentScenario = sc;
        randomGenerator = new MoreRandom(sc.getSeed());
    }

    public void addEntity(SimEntity simEntity) {
        this.simulatedEntities.add(simEntity);
    }

    protected void addEvent(SimEvent event) {
        this.scheduler.add(event);
    }

    protected void removeEvent(SimEvent event) {
        this.scheduler.remove(event);
    }

    /**
     * Initialize the simulation engine with the current scenario's start and end date.
     * It requests the initialization of the scenario.
     * The scenario takes care of initializing all the simulated entities.
     */
    public void init() {
        if (currentScenario != null) {
            this.start = currentScenario.getStart();
            this.end = currentScenario.getEnd();
            this.current = start;
            currentScenario.requestInit();
        }
    }

    /**
     * Initialize the simulation engine with the start and end date.
     * It also initializes all the simulated entities.
     *
     * @param startDate start date of the simulation
     * @param endDate   end date of the simulation
     */
    public void init(LogicalDateTime startDate, LogicalDateTime endDate) {
        this.start = startDate;
        this.end = endDate;
        this.current = startDate;
        simulatedEntities.forEach(SimEntity::requestInit);
    }

    public void simulate() {
        Logger.Information(this, "simulate", "current = " + this.current);
        while (hasNextEvent()) {
            Logger.Information(this, "simulate", "events.size() = " + this.scheduler.size());
            SimEvent event = this.scheduler.first();
            this.scheduler.remove(event);
            current = event.getOccurrenceDate();
            Logger.Information(this, "simulate", "event = " + event);
            event.process();
            Logger.Information(this, "simulate", "currentDate = " + this.current);
        }
    }

    public void clean() {
        simulatedEntities.stream().filter(e -> !(e instanceof Scenario)).forEach(SimEntity::terminate);
        scheduler.clear();
        currentScenario = null;
        start = null;
        current = null;
        end = null;

        // Clear memory
        System.gc();
    }

    public void terminate() {
        scheduler.clear();
        simulatedEntities.clear();
        currentScenario = null;
        start = null;
        current = null;
        end = null;

        System.gc();
    }

    private boolean hasNextEvent() {
        for (SimEvent e : scheduler) {
            if (e.getOccurrenceDate().compareTo(end) <= 0) {
                Logger.Information(this, "hasNextEvent", "true");
                return true;
            }
        }
        Logger.Information(this, "hasNextEvent", "false");
        return false;
    }

    @Override
    public LogicalDateTime SimulationDate() {
        return current;
    }

    public List<SimEntity> search(Predicate<SimEntity> query) {
        return simulatedEntities.stream().filter(query).toList();
    }

    @Override
    public ScenarioId getScenarioId() {
        if (currentScenario == null) {
            return null;
        }
        return currentScenario.getID();
    }
}
