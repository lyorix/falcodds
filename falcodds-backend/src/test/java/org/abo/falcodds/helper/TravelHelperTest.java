package org.abo.falcodds.helper;

import org.abo.falcodds.mocks.RouteMock;
import org.abo.falcodds.mocks.TravelMock;
import org.abo.falcodds.model.Route;
import org.abo.falcodds.model.Travel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TravelHelperTest {

    TravelHelper helper;

    @BeforeEach
    public void init() {
        helper = new TravelHelper();
    }

    // getTotalTravelTime

    @Test
    public void givenTravelWithoutRoute_WhenGetTotalTravelTime_ThenResultIsZero() {
        Travel travel = new Travel();

        assertEquals(helper.getTotalTravelTime(travel), 0);
    }

    @Test
    public void givenDefaultTravelWithRoutes__WhenGetTotalTravelTime_ThenResultIsSumOfRoutesTravelTimes() {
        Travel defaultTravelMock = TravelMock.Factory.createDefaultTravelMock();

        assertEquals(helper.getTotalTravelTime(defaultTravelMock), 9);
    }

    // getLastRoute

    @Test
    public void givenDefaultTravelWithRoutes_WhenGetLastRoute_ThenResultIsValid() {
        Travel defaultTravelMock = TravelMock.Factory.createDefaultTravelMock();

        Optional<Route> lastRoute = helper.getLastRoute(defaultTravelMock);
        assertTrue(lastRoute.isPresent() && lastRoute.get().equals(RouteMock.ROUTE_TATOOINE_HOTH));
    }

    @Test
    public void givenTravelWithoutRoute_WhenGetLastRoute_ThenResultIsNotDefined() {
        Travel travel = new Travel();

        Optional<Route> lastRoute = helper.getLastRoute(travel);
        assertFalse(lastRoute.isPresent());
    }

    // getMinCaptureCounter

    @Test
    public void givenTravelsWithCaptureCountersToOneAndTwo_WhenGetMinCaptureCounter_ThenResultIsOne() {
        List<Travel> travelList = Arrays.asList(TravelMock.Factory.createTravelMock(2, 5), TravelMock.Factory.createTravelMock(1, 6));

        assertEquals(helper.getMinCaptureCounter(travelList), 1);
    }

    @Test
    public void givenEmptyTravelList_WhenGetMinCaptureCounter_ThenExceptionIsRaised() {
        ArrayList<Travel> travels = new ArrayList<>();

        assertThrows(NoSuchElementException.class, () -> helper.getMinCaptureCounter(travels));
    }

    // hasReached

    @Test
    public void givenDefaultTravelWithRoutes_WhenHasReachedEndor_ThenResultIsFalse() {
        Travel defaultTravelMock = TravelMock.Factory.createDefaultTravelMock();

        assertFalse(helper.hasReached(defaultTravelMock, "Endor"));
    }

    @Test
    public void givenDefaultTravelWithRoutes_WhenHasReachedHoth_ThenResultIsTrue() {
        Travel defaultTravelMock = TravelMock.Factory.createDefaultTravelMock();

        assertTrue(helper.hasReached(defaultTravelMock, "Hoth"));
    }
}
