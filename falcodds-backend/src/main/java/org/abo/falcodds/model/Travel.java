package org.abo.falcodds.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Travel {

    private List<Route> routes = new ArrayList<>();

    private Integer remainingAutonomy = 0;

    private Integer captureCounter = 0;

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<Route> getRoutes() {
        return this.routes;
    }

    public void addRoute(Route route) {
        this.routes.add(route);
    }

    public Integer getRemainingAutonomy() {
        return remainingAutonomy;
    }

    public void setRemainingAutonomy(Integer remainingAutonomy) {
        this.remainingAutonomy = remainingAutonomy;
    }

    public Integer getCaptureCounter() {
        return captureCounter;
    }

    public void setCaptureCounter(Integer captureCounter) {
        this.captureCounter = captureCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travel travel = (Travel) o;
        return routes.equals(travel.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes);
    }

    @Override
    public String toString() {
        return "Travel{" +
                "routes=" + routes +
                ", remainingAutonomy=" + remainingAutonomy +
                ", captureCounter=" + captureCounter +
                '}';
    }
}