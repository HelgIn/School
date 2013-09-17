package com.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Train")
public class Train implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private long number;
    private int count;
    private long route;

    public Train() {
    }

    public Train(long number, int count) {
        this.number = number;
        this.count=count;
    }
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name="id")
    public long getId() {
        return id;
    }

    @Column(name="number")
    public long getNumber() {
        return number;
    }

    @Column(name="count")
    public int getCount() {
        return count;
    }

    @Column(name="route")
    public long getRoute() {
        return route;
    }

    public void setId(long id) {
        this.id= id;
    }
    public void setNumber(long number) {
        this.number = number;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public void setRoute(long route) {
        this.route = route;
    }


}