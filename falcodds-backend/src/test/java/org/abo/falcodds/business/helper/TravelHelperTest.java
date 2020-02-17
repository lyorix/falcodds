package org.abo.falcodds.business.helper;

import org.abo.falcodds.business.model.Travel;
import org.abo.falcodds.business.model.TravelStep;
import org.abo.falcodds.mocks.TravelMock;
import org.abo.falcodds.mocks.TravelStepMock;
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

        Optional<TravelStep> lastStep = helper.getLastStep(defaultTravelMock);
        assertTrue(lastStep.isPresent() && lastStep.get().equals(TravelStepMock.HOTH_STEP));
    }

    @Test
    public void givenTravelWithoutRoute_WhenGetLastRoute_ThenResultIsNotDefined() {
        Travel travel = new Travel();

        Optional<TravelStep> lastStep = helper.getLastStep(travel);
        assertFalse(lastStep.isPresent());
    }

    // getMinCaptureCounter

    @Test
    public void givenTravelsWithCaptureCountersToOneAndTwo_WhenGetMinCaptureCounter_ThenResultIsOne() {
        List<Travel> travelList = Arrays.asList(TravelMock.Factory.createDefaultTravelMock(2, 5), TravelMock.Factory.createDefaultTravelMock(1, 6));

        assertEquals(helper.getMinCaptureCounter(travelList), 1);
    }

    @Test
    public void givenEmptyTravelList_WhenGetMinCaptureCounter_ThenExceptionIsRaised() {
        List<Travel> travels = new ArrayList<>();

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
