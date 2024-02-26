package tatooine.Client;

import tatooine.Workshop.Workshop;

/**
 * One record of a client's visit paid to a workshop.
 * Each time a client attends a workshop, one of these records can be created and added to the client's history.
 * It saves :
 * - the workshop visited,
 * - the efficiency of the visit (i.e. the number of minutes the client remained in the workshop),
 * - the perfect efficiency of the visit (i.e. the number of minutes the client should have remained in the workshop, according to its prescription).
 *
 * @param workshop the workshop the client attended
 * @param efficiency the real efficiency of the visit
 * @param perfectEfficiency the theoretic perfect efficiency of the workshop
 */
public record ClientHistory(Workshop workshop, int efficiency, int perfectEfficiency) {
}
