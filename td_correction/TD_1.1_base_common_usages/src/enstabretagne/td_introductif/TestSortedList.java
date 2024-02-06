/*
 *
 */
package enstabretagne.td_introductif;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.simulation.basics.SortedList;

// TODO: Auto-generated Javadoc

/**
 * The Class TestSortedList.
 */
public class TestSortedList implements Comparable<TestSortedList> {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        SortedList<TestSortedList> sl = new SortedList<TestSortedList>();

        //ajout des objets dans le d�sordre des sommes de i et j
        sl.add(new TestSortedList(10, 20));
        sl.add(new TestSortedList(8, 6));
        sl.add(new TestSortedList(1, 2));
        sl.add(new TestSortedList(1, -5));
        sl.add(new TestSortedList(5, 12));
        sl.add(new TestSortedList(-71, 2));

        //affichage dans l'ordre croissant des sommes de i et j
        for (TestSortedList tsl : sl)
            System.out.println(tsl);

        SortedList<LogicalDateTime> slLDT = new SortedList<LogicalDateTime>();

        //ajout des objets dans le d�sordre des dates
        slLDT.add(LogicalDateTime.Zero.add(LogicalDuration.ofDay(4)));
        slLDT.add(LogicalDateTime.Zero);
        slLDT.add(LogicalDateTime.Zero.add(LogicalDuration.ofDay(8)));
        slLDT.add(LogicalDateTime.Zero.add(LogicalDuration.ofDay(6)));

        //affichage dans l'ordre des dates
        for (LogicalDateTime ldt : slLDT)
            System.out.println(ldt);

    }

    /**
     * The i.
     */
    private int i;

    /**
     * The j.
     */
    private int j;

    /**
     * Instantiates a new test sorted list.
     *
     * @param i the i
     * @param j the j
     */
    public TestSortedList(int i, int j) {
        super();
        this.i = i;
        this.j = j;
    }

    /**
     * Gets the i.
     *
     * @return the i
     */
    public int getI() {
        return i;
    }

    /**
     * Gets the j.
     *
     * @return the j
     */
    public int getJ() {
        return j;
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(TestSortedList sl) {
        if (i > sl.getI() && j > sl.getJ() || (i + j) > (sl.getI() + sl.getJ()))
            return 1;
        if (i < sl.getI() && j < sl.getJ() || (i + j) < (sl.getI() + sl.getJ()))
            return -1;
        else
            return 0;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "i=" + i + " j=" + j + " somme=" + (i + j);
    }


}
