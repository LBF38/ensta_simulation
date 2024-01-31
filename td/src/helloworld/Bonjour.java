package helloworld;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;

public class Bonjour extends SimEvent {
    public Bonjour(LogicalDateTime scheduledDate, Etudiant from, Etudiant to) {
        super(scheduledDate, from, to);
    }

    @Override
    public void process() {
        Logger.Information(this, "process", "Bonjour !" + this.from.toString());
    }
}
