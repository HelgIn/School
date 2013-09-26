package com.dto;

import java.io.Serializable;

public class AddRouteInfo implements Serializable {
    private String name;

    public AddRouteInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
