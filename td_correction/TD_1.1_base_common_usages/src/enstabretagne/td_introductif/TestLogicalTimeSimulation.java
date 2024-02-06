/**
 * Classe TestLogicalTimeSimulation.java
 *
 * @author Olivier VERRON
 * @version 1.0.
 */
package enstabretagne.td_introductif;


import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

// TODO: Auto-generated Javadoc

/**
 * The Class TestLogicalTimeSimulation.
 */
public class TestLogicalTimeSimulation {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        String dateMalFaite = "10/12/2014 10;34:47.6789";
        System.out.println(LogicalDateTime.EstBienStructuree(dateMalFaite));

        LogicalDateTime ldt = new LogicalDateTime("10/12/2014 10:34:47.6789");

        LogicalDateTime ldt1 = ldt.truncateToDays().add(LogicalDuration.ofHours(1));
        LogicalDateTime ldt2 = ldt.truncateToDays().add(LogicalDuration.ofHours(2));
        System.out.println(ldt1.compareTo(ldt2));

        System.out.println(ldt);
        LogicalDuration ld = LogicalDuration.ofMinutes(6500).add(LogicalDuration.ofSeconds(12.67));
        System.out.println(ld);
        ld = LogicalDuration.ofSeconds(Math.PI);
        System.out.println(ld);
        System.out.println(ldt.add(ld));

    }

}

