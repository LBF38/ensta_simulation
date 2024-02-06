/*
 *
 */
package enstabretagne.td_introductif;

import enstabretagne.base.logger.IRecordable;
import enstabretagne.base.logger.Logger;

// TODO: Auto-generated Javadoc

/**
 * The Class TestLoggerSimple.
 */
public class TestLoggerSimple {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        //Version compliqu�e utilisant les classes anonymes
        Logger.Data(new IRecordable() {

            @Override
            public String[] getTitles() {
                return new String[]{"Mod�le", "Marque"};
            }

            @Override
            public String[] getRecords() {
                // TODO Auto-generated method stub
                return new String[]{"807", "Peugeot"};
            }

            @Override
            public String getClassement() {
                return "Voiture";
            }
        });

        //Version simplifi�e d'usage du logger
        Logger.DataSimple("Test", "Nom", "Pr�nom", "Age");
        Logger.DataSimple("Test", "SKYWALKER", "Anakin", 30.0);
        Logger.DataSimple("Test", "KENOBI", "ObiOne", 80);
        Logger.DataSimple("Test", "MASTER", "Yoda", 900);
        Logger.DataSimple("TestON", "Name", "Power");
        Logger.DataSimple("TestON", "SAURON", 8.0);
        Logger.DataSimple("TestON", "GANDALF", 18.0);
        Logger.Information(null, "main", "cela fonctionnne!");

        Logger.Terminate();
    }
}
