package helloworld;

import engine.EntiteSimulee;
import engine.SimuEngine;
import enstabretagne.base.time.LogicalDuration;

public class Etudiant extends EntiteSimulee {
    public Etudiant(SimuEngine engine, EtudiantInitData initData) {
        super(engine, initData);
//        this.send(new Bonjour(LogicalDateTime.Now(), this, null), this);
    }

    @Override
    public void init() {
        send(new Bonjour(this.now().add(LogicalDuration.ofMinutes(15)), this, null));
    }
}
