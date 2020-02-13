package org.abo.falcodds.helper;

import org.abo.falcodds.model.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RouteHelperTest {

    RouteHelper helper;

    @BeforeEach
    public void init() {
        helper = new RouteHelper();
    }

    // isInnerRoute

    @Test
    public void givenRouteOriginMatchingPlanet_WhenIsInnerRoute_ThenResultIsTrue() {
        Route route = new Route("Tattoine", "Dagobah", 1);

        assertTrue(helper.isInnerRoute(route, "Tattoine"));
    }

    @Test
    public void givenRouteDestinationMatchingPlanet_WhenIsInnerRoute_ThenResultIsTrue() {
        Route route = new Route("Tattoine", "Dagobah", 1);

        assertTrue(helper.isInnerRoute(route, "Dagobah"));
    }

    @Test
    public void givenRouteNotMatchingPlanet_WhenIsInnerRoute_ThenResultIsFalse() {
        Route route = new Route("Tattoine", "Dagobah", 1);

        assertFalse(helper.isInnerRoute(route, "Endor"));
    }

    // isLandingRoute

    @Test
    public void givenRouteOriginMatchingRouteDestination_WhenIsLandingRoute_ThenResultIsTrue() {
        Route route = new Route("Tattoine", "Tattoine", 1);

        assertTrue(helper.isLandingRoute(route));
    }

    @Test
    public void givenRouteOriginNotMatchingRouteDestination_WhenIsLandingRoute_ThenResultIsFalse() {
        Route route = new Route("Tattoine", "Dagobah", 1);

        assertFalse(helper.isLandingRoute(route));
    }

    // createLandingRoutes

    @Test
    public void givenTwoRoutesWithTreePlanet_WhenCreateLandingRoutes_ThenThreeLandingRouteAreCreated() {
        List<Route> routeList = Arrays.asList(new Route("Tatooine", "Dagobah", 1), new Route("Tatooine", "Hoth", 1));

        List<Route> landingRoutes = helper.createLandingRoutes(routeList);
        assertEquals(landingRoutes.size(), 3);
        assertTrue(landingRoutes.stream().allMatch(route -> route.getDestination().equals("Tatooine") && route.getDestination().equals("Tatooine") ||
                route.getDestination().equals("Dagobah") && route.getDestination().equals("Dagobah") ||
                route.getDestination().equals("Hoth") && route.getDestination().equals("Hoth")
        ));
    }

    // createInnerRoutes

    @Test
    public void givenFiveRoutes_WhenCreateInnerRoutesForDagobah_ThenResultContainsTwoRoutes() {
        List<Route> routeList = Arrays.asList(
                new Route("Tatooine", "Tatooine", 1),
                new Route("Tatooine", "Dagobah", 1),
                new Route("Dagobah", "Dagobah", 1),
                new Route("Tatooine", "Hoth", 1),
                new Route("Hoth", "Hoth", 1)
        );

        List<Route> innerRouteList = helper.createInnerRoutes(routeList, "Dagobah");
        assertEquals(innerRouteList.size(), 2);
    }

    @Test
    public void givenFiveRoutes_WhenCreateInnerRoutesForTatooine_ThenResultContainsThreeRoutes() {
        List<Route> routeList = Arrays.asList(
                new Route("Tatooine", "Tatooine", 1),
                new Route("Tatooine", "Dagobah", 1),
                new Route("Dagobah", "Dagobah", 1),
                new Route("Tatooine", "Hoth", 1),
                new Route("Hoth", "Hoth", 1)
        );

        List<Route> innerRouteList = helper.createInnerRoutes(routeList, "Tatooine");
        assertEquals(innerRouteList.size(), 3);
    }
}
