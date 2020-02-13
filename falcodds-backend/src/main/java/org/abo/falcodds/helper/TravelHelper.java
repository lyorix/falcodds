package org.abo.falcodds.helper;

import org.abo.falcodds.model.Route;
import org.abo.falcodds.model.Travel;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TravelHelper {

    public Integer getTotalTravelTime(Travel travel) {
        return travel.getRoutes().stream().map(Route::getTravelTime).reduce(0, Integer::sum);
    }

    public Optional<Route> getLastRoute(Travel travel) {
        return travel.getRoutes().size() > 0 ?
                Optional.of(travel.getRoutes().get(travel.getRoutes().size() - 1)) :
                Optional.empty();
    }

    public Integer getMinCaptureCounter(List<Travel> travels) {
        return travels.stream().mapToInt(Travel::getCaptureCounter).min().getAsInt();
    }

    public boolean hasReached(Travel travel, String planet) {
        return getLastRoute(travel).get().getDestination().equals(planet);
    }
}
