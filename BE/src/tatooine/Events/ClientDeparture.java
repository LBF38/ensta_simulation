package tatooine.Events;

import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import tatooine.Client.Client;

public class ClientDeparture extends SimEvent {
    public ClientDeparture(LogicalDateTime occurrenceDate, Client simEntity, SimEntity to) {
        super(occurrenceDate, simEntity, to);
    }

    public ClientDeparture(LogicalDateTime occurrenceDate, Client from) {
        super(occurrenceDate, from, null);
    }

    @Override
    public void process() {
        Logger.Information(this, "ClientDeparture", "Client departed at " + this.getOccurrenceDate());
        // TODO: kill the client and/or remove it from the simulation
        // => call client.terminate() ?
    }
}
