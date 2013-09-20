package com.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Journey")
public class Journey {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment" )
    @Column(name="id")
    private long id;
    private int availableSeats;
    private String arrivalDate;
    private long routeId;

    Journey() {

    }

    Journey(int availableSeats, String arrivalDate, long routeId) {
        this.arrivalDate = arrivalDate;
        this.availableSeats = availableSeats;
        this.routeId = routeId;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    @Column(name="arrival_date")
    public String getArrivalDate() {
        return arrivalDate;
    }

    @Column(name="route_id")
    public long getRouteId() {
        return routeId;
    }
}
