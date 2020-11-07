package com.company;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Controls all flights and passengers transfers.
 */
public class Airport {

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

    public ArrayList<Passenger> getCurrentPassengers() {
        return CurrentPassengers;
    }

    /**
     * Simulating of waiting at terminal
     */
    private void StayAtTerminal() throws InterruptedException {
        TerminalsSemaphore.acquire();

        Thread.sleep(TerminalStopDuration);

        TerminalsSemaphore.release();
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

        StayAtTerminal();

        AddPassenger(passenger);
    }

    /**
     * Releases passenger from airport and frees one terminal.
     *
     * @param passenger passenger to remove.
     * @throws InvalidParameterException if passenger was not registered in airport.
     */
    public synchronized void ReleasePassenger(Passenger passenger) throws InvalidParameterException, InterruptedException {

        StayAtTerminal();

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
