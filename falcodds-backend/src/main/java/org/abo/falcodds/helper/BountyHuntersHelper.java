package org.abo.falcodds.helper;

import org.abo.falcodds.model.EmpireConfiguration;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BountyHuntersHelper {

    public boolean hasBountyHunters(EmpireConfiguration empireConfiguration, String planet, Integer day) {
        return empireConfiguration.getBountyHunters().stream()
                .anyMatch(bountyHunter -> bountyHunter.getDay().equals(day) && bountyHunter.getPlanet().equals(planet));
    }
}
