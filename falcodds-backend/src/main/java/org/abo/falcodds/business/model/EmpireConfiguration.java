package org.abo.falcodds.business.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class EmpireConfiguration {

    private Integer countdown;

    @JsonProperty("bounty_hunters")
    private List<BountyHunters> bountyHunters;

    public EmpireConfiguration() {}

    public EmpireConfiguration(Integer countdown, List<BountyHunters> bountyHunters) {
        this.countdown = countdown;
        this.bountyHunters = bountyHunters;
    }

    public Integer getCountdown() {
        return countdown;
    }

    public List<BountyHunters> getBountyHunters() {
        return bountyHunters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpireConfiguration that = (EmpireConfiguration) o;
        return countdown.equals(that.countdown) &&
                bountyHunters.equals(that.bountyHunters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countdown, bountyHunters);
    }

    @Override
    public String toString() {
        return "EmpireConfiguration{" +
                "countdown=" + countdown +
                ", bountyHunters=" + bountyHunters +
                '}';
    }
}