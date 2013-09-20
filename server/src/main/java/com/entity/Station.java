package com.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="Station")
public class Station {
    private long id;
    private String name;

    Station() {

    }

    Station(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment")
    @Column(name="id")
    public long getId() {
        return id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

}
