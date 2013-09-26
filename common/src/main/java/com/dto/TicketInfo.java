package com.dto;

import entity.Ticket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TicketInfo implements Serializable{
    private String name;
    private String surname;
    private String date;
    private long journeyID;

    List<BuyTicketObject> ticketList;


    public TicketInfo() {
        ticketList = new ArrayList<BuyTicketObject>();
    }

    public List<BuyTicketObject> getTicketList() {
        return ticketList;
    }

    public TicketInfo(String name, String surname, String date, long journeyID) {
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
