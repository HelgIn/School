package com.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StartInfo implements Serializable {
    static final long serialVersionUID = -2098940495326113907L;
    private List<String> stations = new ArrayList<String>();

    public StartInfo() {
        stations.add("SPB");
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public List<String> getStations() {
        return stations;
    }

    @Override
    public String toString() {
        return stations.get(0);
    }
}
