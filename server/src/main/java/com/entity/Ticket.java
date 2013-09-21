package com.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="Ticket")
public class Ticket {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment" )
    @Column(name ="id")
    private int id_book;


    @ManyToOne
    private Passenger passenger;
    public Passenger getPassenger() {
        return passenger;
    }
    public void setPassenger(Passenger id_passenger) {
        this.passenger = id_passenger;
    }


    @ManyToOne
    private Journey journey;
    public Journey getJourney() {
        return journey;
    }
    public void setJourney(Journey id_journey) {
        this.journey = id_journey;
    }




}
