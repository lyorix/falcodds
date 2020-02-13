package org.abo.falcodds.mocks;

import org.abo.falcodds.model.Travel;

import java.util.Arrays;

public class TravelMock {

    public static class Factory {

        public static Travel createDefaultTravelMock() {
            Travel travel = new Travel();
            travel.setRoutes(Arrays.asList(RouteMock.ROUTE_TATOOINE_DAGOBAH, RouteMock.ROUTE_TATOOINE_HOTH));
            return travel;
        }

        public static Travel createTravelMock(Integer captureCounter, Integer remainingAutonomy) {
            Travel travel = new Travel();
            travel.setRoutes(Arrays.asList(RouteMock.ROUTE_TATOOINE_DAGOBAH, RouteMock.ROUTE_TATOOINE_HOTH));
            travel.setCaptureCounter(captureCounter);
            travel.setRemainingAutonomy(remainingAutonomy);
            return travel;
        }
    }
}
