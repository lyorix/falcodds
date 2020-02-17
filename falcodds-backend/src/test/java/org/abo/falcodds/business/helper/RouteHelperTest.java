package org.abo.falcodds.business.helper;

import org.abo.falcodds.domain.model.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
