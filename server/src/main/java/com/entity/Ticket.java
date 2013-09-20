package com.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="Ticket")
public class Ticket {
    private long id;
    private long journeyId;
    private long userId;

    Ticket(){

    }

    Ticket(long userId, long journeyId) {
        this.userId = userId;
        this.journeyId = journeyId;
    }
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment")
    @Column(name="id")
    public long getId() {
        return id;
    }

    @Column(name="journey_id")
    public long getJourneyId() {
        return  journeyId;
    }

    @Column(name="user_id")
    public long getUserId() {
        return userId;
    }
}
