package com.dto;


import java.io.Serializable;

public class AddStationInfo implements Serializable {
    private String name;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AddStationInfo(String name) {
        this.name = name;
        message = null;
    }

    public String getName() {
        return name;
    }
}
