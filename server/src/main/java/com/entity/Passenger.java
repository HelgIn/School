package com.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="Passenger")
public class Passenger {

    private long id;
    private String name;
    private String secondName;
    private String date;

    Passenger() {

    }

    Passenger(String name, String lastName, String date) {
        this.name = name;
        this.secondName = secondName;
        this.date = date;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment")
    @Column(name="id")
    public long getID() {
       return id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    @Column(name="second_name")
    public String getSecondName() {
        return secondName;
    }

    @Column(name="date")
    public String getDate() {
        return date;
    }
}
