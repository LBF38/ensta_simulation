/**
 * Classe PICalculation.java
 *
 * @author Olivier VERRON
 * @version 1.0.
 */
package enstabretagne.td_introductif;

import enstabretagne.base.math.MoreRandom;

// TODO: Auto-generated Javadoc

/**
 * The Class PICalculation.
 */
public class PICalculation {

    /** The r. */
    MoreRandom r;

    /** The nombre lances. */
    int nombreLances;

    /**
     * Instantiates a new PI calculation.
     *
     * @param nombreLances the nombre lances
     */
    public PICalculation(int nombreLances) {
        r = new MoreRandom(5);
        this.nombreLances = nombreLances;
    }

    /**
     * Calculer PI.
     */
    public void calculerPI() {
        int nbLancesOK = 0;
        double PI;

        for (int i = 0; i < nombreLances; i++) {
            double x = r.nextDouble();
            double y = r.nextDouble();

            double rayon = Math.sqrt(x * x + y * y);
            if (rayon < 1) {
                nbLancesOK++;
            }
        }

        PI = ((double) nbLancesOK) / nombreLances * 4;
        System.out.println(PI);

    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        PICalculation pi = new PICalculation(100000);
        pi.calculerPI();
    }

}

