package com.dto;


import java.io.Serializable;

public class AddStationInfo implements Serializable {
    private String name;

    public AddStationInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
