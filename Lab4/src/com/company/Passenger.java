package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.function.Predicate;

/**
 * Describes passenger behaviour.
 */
public class Passenger implements Runnable {

    /**
     * Passenger's name
     */
    private String Name;

    /**
     * Used during ticket swaps.
     */
    private static Semaphore TicketsSwapSemaphore = new Semaphore(2,false);

    private static ArrayBlockingQueue<Passenger> TicketSwapRequestQueue = new ArrayBlockingQueue<Passenger>(1000);

    /**
     * Passenger's ticket.
     */
    private ArrayList<Ticket> Tickets;

    /**
     * Airport to use.
     */
    private Airport Airport;

    /**
     * Time taken for flight.
     */
    private int FlightDuration = 1000;

    /**
     * Time taken to wait in waiting room.
     */
    private int WaitingRoomDuration = 1000;

    /**
     * Max time difference from from current time and ticket expiration time to take flight.
     * If difference is higher then passenger will keep waiting.
     */
    private int TimeDifferenceToTakeFlight = 1000;

    public String getName() {
        return Name;
    }

    public ArrayList<Ticket> getTickets() {
        return Tickets;
    }

    public com.company.Airport getAirport() {
        return Airport;
    }

    public Passenger(String name, ArrayList<Ticket> tickets, com.company.Airport airport) {
        Name = name;
        Tickets = tickets;
        Airport = airport;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "Name='" + Name + '\'' +
                ", Tickets=" + Tickets +
                ", Airport=" + Airport +
                ", FlightDuration=" + FlightDuration +
                ", WaitingRoomDuration=" + WaitingRoomDuration +
                ", TimeDifferenceToTakeFlight=" + TimeDifferenceToTakeFlight +
                '}';
    }

    @Override
    public void run() {

        System.out.println(Name + ": Started. Entering airport");

        Airport.AddPassenger(this);

        System.out.println(Name + ": Entered airport");

        Random random = new Random();

        while (!Tickets.isEmpty()) {

            try {
                System.out.println(Name + ": Waiting in waiting room");

                Thread.sleep(WaitingRoomDuration);

                System.out.println(Name + ": Finished to wait in waiting room");

               boolean wantToExchange =  random.nextBoolean();

               if(wantToExchange){
                   TicketsSwapSemaphore.acquire();
                   TicketSwapRequestQueue.add(this);

                   while (TicketSwapRequestQueue.size()!=2){
                       Thread.sleep(200);
                   }

               }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long currentTime = new Date().getTime();

            Tickets.removeIf(new Predicate<Ticket>() {
                @Override
                public boolean test(Ticket ticket) {

                    if (ticket.IsExpired(new Date(currentTime))) {
                        System.out.println(Name + ": Ticket to " + ticket.getDestination() + " expired."
                                + "Expiration date: " + ticket.getExpirationDate() +". Removing from list");
                        return true;
                    }
                    return false;
                }
            });

            boolean canTravel = false;

            for (int i = 0; i < Tickets.size(); i++) {
                if (Tickets.get(i).getExpirationDate().getTime() - currentTime <= TimeDifferenceToTakeFlight) {
                    canTravel = true;

                    System.out.println(Name + ": Found  nearest flight: " + Tickets.get(i));

                    Tickets.remove(i);
                    break;
                }
            }

            if (!canTravel) {
                System.out.println(Name + ": no tickets are available for now. " + new Date(currentTime) + " .Keep waiting");

                continue;
            }

            System.out.println(Name + ": going to terminal to leave airport");

            try {
                Airport.ReleasePassenger(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Name + ": flying started");
            try {
                Thread.sleep(2 * FlightDuration);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println(Name + ": flying finished");

            System.out.println(Name + ": going to terminal to enter airport");

            try {
                Airport.ReceivePassenger(this);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }

            System.out.println(Name + ": entered  in airport.");

        }

        System.out.println(Name + ": Exiting airport.  No more tickets left");

        Airport.RemovePassenger(this);

        System.out.println(Name + ": ended");

    }
}
