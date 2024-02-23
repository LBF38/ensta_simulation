package tatooine.Events;

import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import tatooine.Client.Client;
import tatooine.Client.InitClient;

public class CreateClient extends SimEvent {
    public CreateClient(LogicalDateTime occurrenceDate, SimEntity simEntity, SimEntity to) {
        super(occurrenceDate, simEntity, to);
    }

    public CreateClient(LogicalDateTime occurrenceDate, SimEntity from) {
        super(occurrenceDate, from, null);
    }

    @Override
    public void process() {
        Logger.Information(this, "CreateClient", "Client created at " + this.getOccurrenceDate());

        InitClient init = new InitClient("Client");
        Client client = new Client(this.from.getEngine(), init);
        client.requestInit();

        this.from.send(new ClientDeparture(this.getOccurrenceDate().add(LogicalDuration.ofDay(21)), client));
    }
}
