package com.dto;

import objects.StationAnswerObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class StationInfo implements Serializable{
    private String station;

    public StationInfo(String station) {
        this.station = station;
        result = new ArrayList<StationAnswerObject>();
    }

    public String getStation() {
        return station;
    }


    private List<StationAnswerObject> result;

    public void add(StationAnswerObject stationAnswerObject) {
        result.add(stationAnswerObject);
    }

    public List<StationAnswerObject> getResult() {
        return result;
    }

    public void print() {
        System.out.println("*************************");
        for(StationAnswerObject r : result) {
            System.out.println(r);
        }
        System.out.println("*************************");
    }

}
