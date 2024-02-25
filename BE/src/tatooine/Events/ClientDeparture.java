package tatooine.Events;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import tatooine.Client.Client;

public class ClientDeparture extends SimEvent<Client> {
    public ClientDeparture(LogicalDateTime occurrenceDate, Client from) {
        super(occurrenceDate, from, null);
    }

    @Override
    public void process() {
        Logger.Information(this, "ClientDeparture", "Client departed at " + this.getOccurrenceDate());
        // TODO: kill the client and/or remove it from the simulation
        // => call client.terminate() ?
        this.from.logHistory();
    }
}
