package helloworld;

import engine.SimuEngine;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;

public class Main {
    public static void main(String[] args) {
        Logger.load();
        // initialize the simulation engine.
        LogicalDateTime start_simu = new LogicalDateTime("24/01/2024 10:34:00.0000");
        LogicalDateTime end_simu = new LogicalDateTime("24/01/2024 11:00:00.0000");
        SimuEngine engine = new SimuEngine(start_simu, end_simu);

        // initialize the entities to simulate.
        Etudiant etudiant1 = new Etudiant(engine, new EtudiantInitData("The Matrix", MovieGenre.Action));
        Etudiant etudiant2 = new Etudiant(engine, new EtudiantInitData("Saw", MovieGenre.Horror));
        Etudiant etudiant3 = new Etudiant(engine, new EtudiantInitData("The Matrix", MovieGenre.Action));


        // init & run the engine loop.
        engine.init();
        engine.simulate();
        Logger.Terminate();
    }
}
