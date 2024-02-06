package helloworld;

import engine.SimEngine;
import engine.SimEntity;
import enstabretagne.base.time.LogicalDuration;

public class Etudiant extends SimEntity {
    public Etudiant(SimEngine engine, EtudiantInitData initData) {
        super(engine, initData);
//        this.send(new Bonjour(LogicalDateTime.Now(), this, null), this);
    }

    @Override
    public void init() {
        send(new Bonjour(this.now().add(LogicalDuration.ofMinutes(15)), this, null));
    }
}
