package org.abo.falcodds.business.model;

import java.util.Objects;

public class BountyHunters {

    private String planet;

    private Integer day;

    public BountyHunters() {}

    public BountyHunters(String planet, Integer day) {
        this.planet = planet;
        this.day = day;
    }

    public String getPlanet() {
        return planet;
    }

    public Integer getDay() {
        return day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BountyHunters that = (BountyHunters) o;
        return planet.equals(that.planet) &&
                day.equals(that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planet, day);
    }

    @Override
    public String toString() {
        return "BountyHunter{" +
                "planet='" + planet + '\'' +
                ", day=" + day +
                '}';
    }
}