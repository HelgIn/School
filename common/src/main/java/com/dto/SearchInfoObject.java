package com.dto;


import java.io.Serializable;
import java.util.Date;

public class SearchInfoObject implements Serializable{
    long id;
    private String name;
    private Date arrivalTime;
    public int availableSeats;


    public SearchInfoObject(long id, String name, Date arrivalTime, int availableSeats) {
        this.id = id;
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
    }

    public String getName() {
        return name;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public long getId() {
        return id;
    }
}
