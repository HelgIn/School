package com.dto;

import java.io.Serializable;

public class AddRouteInfo implements Serializable {
    private String name;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public AddRouteInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
