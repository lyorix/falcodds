package org.abo.falcodds.domain.model;

import java.util.Objects;

public class Route {

    private String origin;

    private String destination;

    private Integer travelTime;

    public Route() {}

    public Route(String origin, String destination, Integer travelTime) {
        this.origin = origin;
        this.destination = destination;
        this.travelTime = travelTime;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public int getTravelTime() {
        return travelTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return origin.equals(route.origin) &&
                destination.equals(route.destination) &&
                travelTime.equals(route.travelTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, destination, travelTime);
    }

    @Override
    public String toString() {
        return "Route{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", travelTime=" + travelTime +
                '}';
    }
}