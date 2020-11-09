package com.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.function.Predicate;
import org.apache.log4j.*;


/**
 * Describes passenger behaviour.
 */
public class Passenger implements Runnable {

    /**
     * Logger for passenger.
     */
    private static final Logger PASSENGER_LOGGER = LogManager.getLogger("Passenger");

    /**
     * Passenger's name
     */
    private String Name;

    /**
     * Used during ticket swaps.
     */
    private static Object PassengersCheckAmountLocker = new Object();

    private static ArrayList<Passenger> TicketSwapRequestList = new ArrayList<>();

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

       PASSENGER_LOGGER.info(Name + ": Started. Entering airport");

        Airport.AddPassenger(this);

       PASSENGER_LOGGER.info(Name + ": Entered airport");

        Random random = new Random();

        while (!Tickets.isEmpty()) {

            try {
               PASSENGER_LOGGER.info(Name + ": Waiting in waiting room");

                Thread.sleep(WaitingRoomDuration);

               PASSENGER_LOGGER.info(Name + ": Finished to wait in waiting room");

                boolean wantToExchange = random.nextBoolean();

                if (wantToExchange) {

                   PASSENGER_LOGGER.info(Name + ": Wants to swap tickets");

                    SwapTickets();
                }

            } catch (InterruptedException e) {
                PASSENGER_LOGGER.error(e.getMessage());
            }

            long currentTime = new Date().getTime();

            Tickets.removeIf(new Predicate<Ticket>() {
                @Override
                public boolean test(Ticket ticket) {

                    if (ticket.IsExpired(new Date(currentTime))) {
                       PASSENGER_LOGGER.info(Name + ": Ticket to " + ticket.getDestination() + " expired."
                                + "Expiration date: " + ticket.getExpirationDate() + ". Removing from list");
                        return true;
                    }
                    return false;
                }
            });

            boolean canTravel = false;

            for (int i = 0; i < Tickets.size(); i++) {
                if (Tickets.get(i).getExpirationDate().getTime() - currentTime <= TimeDifferenceToTakeFlight) {
                    canTravel = true;

                   PASSENGER_LOGGER.info(Name + ": Found  nearest flight: " + Tickets.get(i));

                    Tickets.remove(i);
                    break;
                }
            }

            if (!canTravel) {
               PASSENGER_LOGGER.info(Name + ": no tickets are available for now. " + new Date(currentTime) + " .Keep waiting");

                continue;
            }

           PASSENGER_LOGGER.info(Name + ": going to terminal to leave airport");

            try {
                Airport.ReleasePassenger(this);
            } catch (InterruptedException e) {
                PASSENGER_LOGGER.error(e.getMessage());
            }

           PASSENGER_LOGGER.info(Name + ": flying started");
            try {
                Thread.sleep(2 * FlightDuration);
            } catch (InterruptedException ex) {
               PASSENGER_LOGGER.error(ex.getMessage());
            }

           PASSENGER_LOGGER.info(Name + ": flying finished");

           PASSENGER_LOGGER.info(Name + ": going to terminal to enter airport");

            try {
                Airport.ReceivePassenger(this);
            } catch (InterruptedException ex) {
               PASSENGER_LOGGER.info(ex.getMessage());
            }

           PASSENGER_LOGGER.info(Name + ": entered  in airport.");

        }

       PASSENGER_LOGGER.info(Name + ": Exiting airport.  No more tickets left");

        synchronized (TicketSwapRequestList) {

            Airport.RemovePassenger(this);

            if (Airport.getCurrentPassengers().size() == 1) {

               PASSENGER_LOGGER.info(Name + ": Last passenger left in airport. Notifying it");

                TicketSwapRequestList.notify();
            }
        }

       PASSENGER_LOGGER.info(Name + ": ended");

    }

    /**
     * Doing swap tickets with first found passenger with same goal.
     * @throws InterruptedException
     */
    private void SwapTickets() throws InterruptedException {

        synchronized (TicketSwapRequestList) {

            TicketSwapRequestList.add(this);

            if (TicketSwapRequestList.size() == 2) {

                //Doing swap

                Ticket temp = TicketSwapRequestList.get(0).getTickets().get(0);

               PASSENGER_LOGGER.info(Name + ": Found passenger to swap with ("+ TicketSwapRequestList.get(0).Name+"). " +
                        "Doing swap between " + temp + "(hes) and " + Tickets.get(0)+"(my)");

                long currentTime = new Date().getTime();

                if (temp.IsExpired(new Date(currentTime)) || Tickets.get(0).IsExpired(new Date(currentTime))) {
                   PASSENGER_LOGGER.info(Name + ": One or both tickets expired. Swap canceled");
                }

                TicketSwapRequestList.get(0).Tickets.set(0, Tickets.get(0));

                Tickets.set(0, temp);

                TicketSwapRequestList.clear();

                TicketSwapRequestList.notify();

            } else {
                if (Airport.getCurrentPassengers().size() != 1) {
                    TicketSwapRequestList.wait();
                }

            }


        }

    }
}
