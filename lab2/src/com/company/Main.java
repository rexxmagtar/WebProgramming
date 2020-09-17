package com.company;

import com.company.goodies.Goody;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // write your code here
        PresentManager manager = new PresentManager();

        ArrayList<Goody> present = manager.createPresent();

        System.out.println("Present info");

        manager.printPresentInfo(present);

        System.out.println("");

        manager.sortGoodiesByWeight(present);

        System.out.println("Present sorted by weight info");

        manager.printPresentInfo(present);

        System.out.println("Presents weight = " + manager.getGoodiesWight(present));

        System.out.println("Found specified goodies: \n");

        manager.printPresentInfo(manager.findGoodies(present,
                0,
                100,
                0,
                7,
                3,
                5,
                ""));

    }
}
