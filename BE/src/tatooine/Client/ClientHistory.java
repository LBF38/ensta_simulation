package tatooine.Client;

import tatooine.Workshop.Workshop;

public record ClientHistory(Workshop workshop,
                            int efficiency, int perfectEfficiency) {
}
