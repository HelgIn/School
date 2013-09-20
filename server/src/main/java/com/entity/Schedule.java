package com.entity;


import javax.persistence.*;


@Entity
@Table(name = "Schedule")
public class Schedule {

    private long stationFrom;
    private long stationTo;
    private long delayTime;
    private int orderNo;
    private long routeId;

    Schedule() {

    }

    Schedule(long stationFrom, long stationTo, long delayTime, int orderNo, long routeId) {
        this.delayTime = delayTime;
        this.orderNo = orderNo;
        this.routeId = routeId;
        this.stationFrom = stationFrom;
        this.stationTo = stationTo;

    }

    @Column(name="order_number")
    public int getOrderNo() {
        return orderNo;
    }

    @Column(name="station_from")
    public long getStationFrom() {
        return stationFrom;
    }

    @Column(name="station_to")
    public long getStationTo() {
        return stationTo;
    }

    @Column(name="delay_time")
    public long getDelayTime() {
        return delayTime;
    }

    @Column(name="route_id")
    public long getRouteId() {
        return routeId;
    }

}
