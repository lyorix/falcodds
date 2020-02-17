package org.abo.falcodds.business.helper;

import org.abo.falcodds.domain.model.Route;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RouteHelper {

    public boolean isInnerRoute(Route route, String planet) {
        return route.getOrigin().equals(planet) || route.getDestination().equals(planet);
    }

    public boolean isLandingRoute(Route route) {
        return route.getDestination().equals(route.getOrigin());
    }
}
