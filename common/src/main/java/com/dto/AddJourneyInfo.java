package com.dto;


import java.io.Serializable;

public class AddJourneyInfo implements Serializable {
    private String routeName;
    private int number;
    private int availableSeats;
    private String arrivalTime;
    String message;

    public String getArrivalTime() {
        return arrivalTime;
    }

    public AddJourneyInfo(String routeName, int number, int availableSeats, String arrivalTime) {
        this.routeName = routeName;
        this.number = number;
        this.availableSeats = availableSeats;
        this.arrivalTime = arrivalTime;
        message = null;
    }

    public String getRouteName(){
        return routeName;
    }

    public int getNumber() {
        return  number;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
