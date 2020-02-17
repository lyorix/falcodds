package org.abo.falcodds.business.model;

import java.util.Objects;

public class TravelStep {

    String planet;

    Integer travelTime;

    public TravelStep() {
    }

    public TravelStep(String planet, Integer travelTime) {
        this.planet = planet;
        this.travelTime = travelTime;
    }

    public String getPlanet() {
        return planet;
    }

    public Integer getTravelTime() {
        return travelTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelStep step = (TravelStep) o;
        return planet.equals(step.planet) &&
                travelTime.equals(step.travelTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planet, travelTime);
    }

    @Override
    public String toString() {
        return "Step{" +
                "planet='" + planet + '\'' +
                ", travelTime=" + travelTime +
                '}';
    }
}
