/**
 * Classe IRecordable.java
 *
 * @author Olivier VERRON
 * @version 1.0.
 */
package enstabretagne.base.logger;

// TODO: Auto-generated Javadoc

/**
 * The Interface IRecordable.
 */
public interface IRecordable {

    /**
     * Gets the titles.
     *
     * @return the titles
     */
    /*
     * Renvoie les ent�tes correspondants aux donn�es enregistr�es => permet de donner un nom aux variables du records
     */
    String[] getTitles();

    /**
     * Gets the records.
     *
     * @return the records
     */
    /*
     * renvoie les donn�es sous forme de chaines des donn�es
     */
    String[] getRecords();

    /**
     * Gets the classement.
     *
     * @return the classement
     */
    /*
     * permet de cat�goriser l'enregistrement. Sera utilis� pour classer les donn�es enregistr�es
     */
    String getClassement();
}

