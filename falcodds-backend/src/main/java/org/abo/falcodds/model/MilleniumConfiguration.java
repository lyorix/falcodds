package org.abo.falcodds.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class MilleniumConfiguration {

    private Integer autonomy;

    private String departure;

    private String arrival;

    @JsonProperty("routes_db")
    private String routesDb;

    public Integer getAutonomy() {
        return autonomy;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public String getRoutesDb() {
        return routesDb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MilleniumConfiguration that = (MilleniumConfiguration) o;
        return autonomy.equals(that.autonomy) &&
                departure.equals(that.departure) &&
                arrival.equals(that.arrival) &&
                routesDb.equals(that.routesDb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autonomy, departure, arrival, routesDb);
    }

    @Override
    public String toString() {
        return "MilleniumConfiguration{" +
                "autonomy=" + autonomy +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", routesDb='" + routesDb + '\'' +
                '}';
    }
}