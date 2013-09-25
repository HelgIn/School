package com.dto;


import java.io.Serializable;

public class SearchInfoObject implements Serializable{
    long id;
    private String name;
    private String arrivalTime;
    public int availableSeats;

    public SearchInfoObject(long id, String name, String arrivalTime, int availableSeats) {
        this.id = id;
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
    }

    public String getName() {
        return name;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public long getId() {
        return id;
    }
}
