package td1;

import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.simulation.basics.SortedList;

public class Exercise3 {
    public static void main(String[] args) {
        q1();
        q2();
    }

    private static void q1() {
        SortedList<Toto> list = new SortedList<Toto>();
        list.add(new Toto(3));
        list.add(new Toto(1));
        list.add(new Toto(2));
        list.add(new Toto(4));
        list.add(new Toto(5));
        list.forEach(toto -> System.out.println(toto.value()));
    }

    private static void q2() {
        SortedList<LogicalDateTime> list = new SortedList<LogicalDateTime>();
        list.add(new LogicalDateTime("01/01/2021 00:00:00"));
        list.add(new LogicalDateTime("01/01/2021 00:00:02"));
        list.add(new LogicalDateTime("01/01/2021 00:00:01"));
        list.forEach(ldt -> System.out.println(ldt.toString()));
    }
}
