package com.dto;


import entity.Route;

import java.io.Serializable;
import java.util.List;

public class StationAnswer implements Serializable {
    private List<Route> routes;

    public StationAnswer(List<Route> routes) {
        this.routes = routes;
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
