package com.dto;

public class TicketInfo {
    private String name;
    private String surname;
    private String date;
    private long journeyID;

    TicketInfo(String name, String surname, String date, long journeyID) {
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
}
