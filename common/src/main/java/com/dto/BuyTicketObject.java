package com.dto;


import java.io.Serializable;

public class BuyTicketObject implements Serializable {
    private String name;
    private String surname;
    private String date;
    private long journeyID;





    public BuyTicketObject(String name, String surname, String date, long journeyID) {
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.journeyID = journeyID;
    }

    public String getName() {
        return  name;
    }
    public String getSurname() {
        return  surname;
    }
    public long getJourneyID() {
        return journeyID;
    }
    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Ticket { " + getName() + ", " + getSurname() + ", " + getDate() + ", " + getJourneyID() + " }";
    }
}
