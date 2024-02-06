package enstabretagne.td_introductif;

import enstabretagne.base.math.MoreRandom;

public class TestCours {
    public static void main(String[] args) {
        MoreRandom m = new MoreRandom(8);

        for (int i = 0; i < 1000; i++) {
            System.out.println(m.nextInt(1, 10));
        }


    }
}
