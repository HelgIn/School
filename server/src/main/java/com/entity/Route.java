package com.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Route")
public class Route {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment" )
    @Column(name="id")
    private long id;

    private String name;

    public Route() {

    }

    public Route(String name) {
        this.name = name;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {

        return this.getId() + ": " + this.getName();
    }
}
