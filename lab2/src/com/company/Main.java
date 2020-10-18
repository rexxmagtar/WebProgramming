package com.company;

import com.company.goodies.Goody;

import java.util.ArrayList;

/**
 * Example class demonstrates how the system works
 */
public class Main {

    public static void main(String[] args) {
        // write your code here
        PresentManager manager = new PresentManager();

        ArrayList<Goody> present = manager.createPresent();

        System.out.println("Present info\n "+manager.getPresentInfoString(present));

        System.out.println("");

        manager.sortGoodiesByWeight(present);

        System.out.println("Present sorted by weight info\n" +manager.getPresentInfoString(present));

        System.out.println("Presents weight = " + manager.getGoodiesWight(present));

        System.out.println("Found specified goodies: \n");

       System.out.println( manager.getPresentInfoString(manager.findGoodies(present,
                0,
                100,
                0,
                7,
                3,
                5,
                "")));

    }
}
