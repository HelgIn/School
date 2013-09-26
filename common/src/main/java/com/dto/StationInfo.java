package com.dto;

import java.io.Serializable;


public class StationInfo implements Serializable{
    private String station;

    public StationInfo(String station) {
        this.station = station;
    }

    public String getStation() {
        return station;
    }
}
