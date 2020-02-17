package org.abo.falcodds.business.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Travel {

    private List<TravelStep> steps = new ArrayList<>();

    private Integer remainingAutonomy = 0;

    private Integer captureCounter = 0;

    public Travel() {
    }

    public Travel(List<TravelStep> steps, Integer remainingAutonomy, Integer captureCounter) {
        this.steps = steps;
        this.remainingAutonomy = remainingAutonomy;
        this.captureCounter = captureCounter;
    }

    public List<TravelStep> getSteps() {
        return this.steps;
    }

    public Integer getRemainingAutonomy() {
        return remainingAutonomy;
    }

    public Integer getCaptureCounter() {
        return captureCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travel travel = (Travel) o;
        return steps.equals(travel.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(steps);
    }

    @Override
    public String toString() {
        return "Travel{" +
                "steps=" + steps +
                ", remainingAutonomy=" + remainingAutonomy +
                ", captureCounter=" + captureCounter +
                '}';
    }
}