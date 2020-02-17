package org.abo.falcodds.domain.control;

import org.abo.falcodds.domain.model.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteControlTest {

    RouteControl control;

    @BeforeEach
    public void init() {
        control = new RouteControl();
    }

    // createLandingRoutes

    @Test
    public void givenTwoRoutesWithThreePlanets_WhenCreateLandingRoutes_ThenThreeLandingRoutesAreCreated() {
        List<Route> routeList = Arrays.asList(new Route("Tatooine", "Dagobah", 1), new Route("Tatooine", "Hoth", 1));

        List<Route> landingRoutes = control.createLandingRoutes(routeList);
        assertEquals(landingRoutes.size(), 3);
        assertTrue(landingRoutes.stream().allMatch(route -> route.getDestination().equals("Tatooine") && route.getDestination().equals("Tatooine") ||
                route.getDestination().equals("Dagobah") && route.getDestination().equals("Dagobah") ||
                route.getDestination().equals("Hoth") && route.getDestination().equals("Hoth")
        ));
    }
}
