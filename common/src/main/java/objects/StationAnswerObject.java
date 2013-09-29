package objects;


import entity.Route;

import java.io.Serializable;
import java.util.List;

public class StationAnswerObject implements Serializable {
    private List<Route> routes;

    public StationAnswerObject(List<Route> routes) {
        this.routes = routes;
    }

    public StationAnswerObject(String name, String time, int number) {
        this.name = name;
        this.time = time;
        this.number = number;
    }

    public List<Route> getRoutes() {
        return routes;
    }


    private String name;
    private String time;
    private int number;
    public void  setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }
    public String getTime() {
        return time;
    }
    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return getNumber() + " " + getName() + " " + getTime();
    }
}
