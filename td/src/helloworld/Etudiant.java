package helloworld;

import engine.EntiteSimulee;
import engine.InitData;
import engine.SimuEngine;
import enstabretagne.base.time.LogicalDateTime;

public class Etudiant extends EntiteSimulee {
    public Etudiant(SimuEngine engine, EtudiantInitData initData) {
        super(engine, initData);
        this.addEvent(new Bonjour(LogicalDateTime.Now(), this, null));
    }
}
