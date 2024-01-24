package helloworld;

import engine.SimuEngine;
import enstabretagne.base.time.LogicalDateTime;

public class Main {
    public static void main(String[] args) {
        LogicalDateTime start_simu = new LogicalDateTime("24/01/2024 10:34:00.0000");
        LogicalDateTime end_simu = new LogicalDateTime("24/01/2024 10:40:00.0000");
        SimuEngine engine = new SimuEngine(start_simu, end_simu);
        engine.addEntiteSimulee(new Etudiant(engine, null));
        engine.run();
    }
}
