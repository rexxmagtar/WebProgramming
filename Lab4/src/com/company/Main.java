package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // write your code here
        System.out.println("Starting to work");

        Airport airport = new Airport(1);

        Ticket ticket1 = new Ticket("London", new Date(new Date().getTime() + 10 * 1000));
        Ticket ticket2 = new Ticket("Minsk", new Date(new Date().getTime() + 5 * 1000));
        Ticket ticket3 = new Ticket("New-York", new Date(new Date().getTime() + 30 * 1000));
        Ticket ticket4 = new Ticket("New-York", new Date(new Date().getTime() + 1 * 1000));

        Passenger passenger1 = new Passenger("Ivan",
                new ArrayList<>(Arrays.asList(ticket1,ticket3,ticket4)), airport);

        Passenger passenger2 = new Passenger("Egor",
                new ArrayList<>(Arrays.asList(ticket2,ticket4)), airport);

        Passenger passenger3 = new Passenger("Alex",
                new ArrayList<>(Arrays.asList(ticket1,ticket2,ticket3)), airport);

        Thread thread1 = new Thread(passenger1);
        Thread thread2 = new Thread(passenger2);
        Thread thread3 = new Thread(passenger3);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}
