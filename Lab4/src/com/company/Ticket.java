package com.company;

import java.util.Date;

/**
 * Describes flight ticket info.
 */
public class Ticket {

    /**
     * Flight destination.
     */
    private String Destination;

    /**
     * Max inclusive valid date to use ticket.
     */
    private Date ExpirationDate;

    public String getDestination() {
        return Destination;
    }

    public Date getExpirationDate() {
        return ExpirationDate;
    }

    /**
     * Checks if ticket is still valid or has already expired.
     * @return
     */
    public boolean IsExpired(Date currentDate){

        return currentDate.after(ExpirationDate);

    }

    public Ticket(String destination, Date expirationDate) {
        Destination = destination;
        ExpirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "Destination='" + Destination + '\'' +
                ", ExpirationDate=" + ExpirationDate +
                '}';
    }
}
