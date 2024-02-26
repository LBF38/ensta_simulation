package tatooine.Events;

import engine.SimEntity;
import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import tatooine.Client.Client;
import tatooine.Client.InitClient;

/**
 * The event describing a Client creation.
 */
public class CreateClient extends SimEvent<SimEntity> {
    private final InitClient initClient;

    public CreateClient(LogicalDateTime occurrenceDate, SimEntity from, InitClient initClient) {
        super(occurrenceDate, from, null);
        this.initClient = initClient;
    }

    @Override
    public void process() {
        Logger.Information(this, "CreateClient", "Client created at " + this.getOccurrenceDate());

//        InitClient init = new InitClient("Client", workshops);
        Client client = new Client(this.from.getEngine(), initClient);
        client.requestInit();
        Logger.Data(client);

        // End of the client's cure.
        this.from.send(new ClientDeparture(this.getOccurrenceDate().add(LogicalDuration.ofDay(21)), client));
    }
}
