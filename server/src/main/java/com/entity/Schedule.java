package com.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "Schedule")
public class Schedule {

    private long delayTime;
    private int orderNo;
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy="increment")
    @Column(name="id")
    private long id;
    public Schedule() {

    }

    public Schedule(long delayTime, int orderNo) {
        this.delayTime = delayTime;
        this.orderNo = orderNo;

    }

    @Column(name="order_number")
    public int getOrderNo() {
        return orderNo;
    }


    @Column(name="delay_time")
    public long getDelayTime() {
        return delayTime;
    }

    @ManyToOne
    private Route route;
    public Route getRoute() {
        return route;
    }
    public void setRoute(Route id_route) {
        this.route = id_route;
    }

    @ManyToOne
    private Station stationFrom;
    public Station getStationFrom() {
        return stationFrom;
    }
    public void setStationFrom(Station id_from) {
        this.stationFrom = id_from;
    }

    @ManyToOne
    private Station stationTo;
    public Station getStationTo() {
        return stationTo;
    }
    public void setStationTo(Station id_to) {
        this.stationTo = id_to;
    }

}
