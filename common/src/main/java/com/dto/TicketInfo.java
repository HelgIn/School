package com.dto;

import objects.BuyTicketObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TicketInfo implements Serializable{
//    private String name;
//    private String surname;
//    private String date;
//    private long journeyID;

    private List<BuyTicketObject> ticketList;
    private long journeyId;
    private String message;
    private boolean result;

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(long journeyId) {
        this.journeyId = journeyId;
    }
    public TicketInfo() {
        ticketList = new ArrayList<BuyTicketObject>();
        result = false;
        message = null;
    }

    public List<BuyTicketObject> getTicketList() {
        return ticketList;
    }

    public void addObject(BuyTicketObject buyTicketObject) {
        getTicketList().add(buyTicketObject);
    }

//    public TicketInfo(String name, String surname, String date, long journeyID) {
//        this.name = name;
//        this.surname = surname;
//        this.date = date;
//        this.journeyID = journeyID;
//    }
//
//    public String getName() {
//        return  name;
//    }
//    public String getSurname() {
//        return  surname;
//    }
//    public long getJourneyID() {
//        return journeyID;
//    }
//    public String getDate() {
//        return date;
//    }

//    @Override
//    public String toString() {
//        return "Ticket { " + getName() + ", " + getSurname() + ", " + getDate() + ", " + getJourneyID() + " }";
//    }
}
