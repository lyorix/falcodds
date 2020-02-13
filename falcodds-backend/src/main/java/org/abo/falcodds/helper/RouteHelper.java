package org.abo.falcodds.helper;

import org.abo.falcodds.model.Route;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class RouteHelper {

    public boolean isInnerRoute(Route route, String planet) {
        return route.getOrigin().equals(planet) || route.getDestination().equals(planet);
    }

    public boolean isLandingRoute(Route route) {
        return route.getDestination().equals(route.getOrigin());
    }

    public List<Route> createLandingRoutes(List<Route> routes) {
        List<String> planets = routes.stream().map(r -> r.getOrigin()).collect(Collectors.toList());
        planets.addAll(routes.stream().map(r -> r.getDestination()).collect(Collectors.toList()));
        return planets.stream().distinct().map(planet -> new Route(planet, planet, 1)).collect(Collectors.toList());
    }

    public List<Route> createInnerRoutes(List<Route> allRoutes, String planet) {
        return allRoutes.stream().filter(route -> isInnerRoute(route, planet)).map(route -> route.getDestination().equals(planet) ?
                new Route(route.getDestination(), route.getOrigin(), route.getTravelTime()) :
                new Route(route.getOrigin(), route.getDestination(), route.getTravelTime())).collect(Collectors.toList());
    }
}
