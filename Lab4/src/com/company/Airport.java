package com.company;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Controls all flights and passengers transfers.
 */
public class Airport {


    /**
     * Logger for airport.
     */
    private static final Logger AIRPORT_LOGGER = LogManager.getLogger("Airport");

    /**
     * Number of all terminals in airport
     */
    private int TerminalCount = 1;

    /**
     * Semaphore to control terminals availability
     */
    private Semaphore TerminalsSemaphore;

    /**
     * Current passengers
     */
    private ArrayList<Passenger> CurrentPassengers;

    /**
     * Time to wait at the terminal.
     */
    private int TerminalStopDuration = 1000;

    /**
     * Gets current number of passengers in the airport.
     * @return
     */
    public ArrayList<Passenger> getCurrentPassengers() {
        return CurrentPassengers;
    }

    /**
     * Simulating of waiting at terminal
     */
    private void StayAtTerminal(Passenger passenger) throws InterruptedException {

        AIRPORT_LOGGER.info(passenger.getName() +" came to terminal and started to wait in queue");

        TerminalsSemaphore.acquire();

        AIRPORT_LOGGER.info(passenger.getName() +" entered terminal");

        Thread.sleep(TerminalStopDuration);

        TerminalsSemaphore.release();

        AIRPORT_LOGGER.info(passenger.getName() +" left the terminal");

    }

    public Airport(int terminalCount) {

        TerminalCount = terminalCount;
        TerminalsSemaphore = new Semaphore(TerminalCount, false);

        CurrentPassengers = new ArrayList<>();

    }

    /**
     * Receives new passenger and sends him to first free terminal.
     *
     * @param passenger passenger to to receive.
     * @throws InterruptedException if semaphore acquire was interrupted.
     */
    public synchronized void ReceivePassenger(Passenger passenger) throws InterruptedException {

        AIRPORT_LOGGER.info(passenger.getName() +" came to to airport. Sending him to terminals");

        StayAtTerminal(passenger);

        AddPassenger(passenger);

        AIRPORT_LOGGER.info(passenger.getName() +" entered waiting room");
    }

    /**
     * Releases passenger from airport and frees one terminal.
     *
     * @param passenger passenger to remove.
     * @throws InvalidParameterException if passenger was not registered in airport.
     */
    public synchronized void ReleasePassenger(Passenger passenger) throws InvalidParameterException, InterruptedException {

        StayAtTerminal(passenger);

        RemovePassenger(passenger);
    }

    /**
     * Removes passenger from airport. Simulates passenger exit from airport when used from Passenger class.
     *
     * @param passenger
     */
    public synchronized void RemovePassenger(Passenger passenger) {
        if (!CurrentPassengers.contains(passenger)) {
            throw new InvalidParameterException("Tried to remove passenger. But passenger did not enter the airport");
        }
        CurrentPassengers.remove(passenger);
    }

    /**
     * Add passenger to airport. Simulates airport enter if called by passenger.
     *
     * @param passenger
     */
    public synchronized void AddPassenger(Passenger passenger) {
        if (CurrentPassengers.contains(passenger)) {
            throw new InvalidParameterException("Tried to add passenger. But passenger already entered the airport");
        }
        CurrentPassengers.add(passenger);
    }

}
