/**
 * * Classe LoggerAndProba.java
 *
 * @author Olivier VERRON
 * @version 1.0.
 */
package enstabretagne.td_introductif;

import enstabretagne.base.logger.CategoriesGenerator;
import enstabretagne.base.logger.CategoriesGenerator.Segment;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.logger.ToRecord;
import enstabretagne.base.math.MoreRandom;

// TODO: Auto-generated Javadoc

/**
 * The Class LoggerAndProba.
 */
@ToRecord(name = "TD0")
public class LoggerAndProba {

    /**
     * The cg.
     */
    CategoriesGenerator cg;
    /**
     * The etat gaussien.
     */
    private double etatGaussien;
    /**
     * The random.
     */
    private MoreRandom random;

    /**
     * Instantiates a new logger and proba.
     */
    public LoggerAndProba() {

        Logger.Information(this, "Contructeur", "Construit!");

        cg = new CategoriesGenerator(-5, 5, 20, 2, 2);
        random = new MoreRandom();
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        Logger.load();
        // LoggerAndProba est un moniteur
        LoggerAndProba lap = new LoggerAndProba();
        lap.run();
        // Cloture des logger et qui proc�de notammnt � l'�criture du fichier
        // Excel
        Logger.Terminate();

    }

    /**
     * Gets the etat gaussien.
     *
     * @return the etat gaussien
     */
    @ToRecord(name = "x")
    public double getEtatGaussien() {
        return etatGaussien;
    }

    /**
     * Gets the segment.
     *
     * @return the segment
     */
    @ToRecord(name = "cat")
    public Segment getSegment() {
        return cg.getSegmentOf(etatGaussien);
    }

    /**
     * Run.
     */
    public void run() {
        int i = 0;

        for (i = 0; i < 100; i++) {
            etatGaussien = random.nextGaussian();

            Logger.Data(this);

        }
    }

}
