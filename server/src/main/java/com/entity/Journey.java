package com.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="Journey")
public class Journey {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment")
    @Column(name="id")
    private long id;

    @Column(name="available_seats")
    private int availableSeats;
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Column(name="arrival_time")
    private String arrivalTime;
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Journey(int availableSeats, String arrivalTime) {
        this.availableSeats = availableSeats;
        this.arrivalTime = arrivalTime;
    }

    @ManyToOne
    @JoinColumn(name="route_id")
    private Route route;
    public Route getRoute() {
        return route;
    }
    public void setRoute(Route id_route) {
        this.route = id_route;
    }
}
