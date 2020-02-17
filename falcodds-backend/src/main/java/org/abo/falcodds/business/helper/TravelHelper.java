package org.abo.falcodds.business.helper;

import org.abo.falcodds.business.model.TravelStep;
import org.abo.falcodds.business.model.Travel;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TravelHelper {

    public Integer getTotalTravelTime(Travel travel) {
        return travel.getSteps().stream().map(TravelStep::getTravelTime).reduce(0, Integer::sum);
    }

    public Optional<TravelStep> getLastStep(Travel travel) {
        return travel.getSteps().size() > 0 ?
                Optional.of(travel.getSteps().get(travel.getSteps().size() - 1)) :
                Optional.empty();
    }

    public Integer getMinCaptureCounter(List<Travel> travels) {
        return travels.stream().mapToInt(Travel::getCaptureCounter).min().getAsInt();
    }

    public boolean hasReached(Travel travel, String planet) {
        return getLastStep(travel).get().getPlanet().equals(planet);
    }
}
