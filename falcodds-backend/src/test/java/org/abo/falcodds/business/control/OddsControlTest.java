package org.abo.falcodds.business.control;

import org.abo.falcodds.business.helper.TravelHelper;
import org.abo.falcodds.mocks.TravelMock;
import org.abo.falcodds.business.model.BountyHunters;
import org.abo.falcodds.business.model.EmpireConfiguration;
import org.abo.falcodds.business.model.Travel;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OddsControlTest {

    private static EmpireConfiguration empirePlan;

    OddsControl control;

    TravelControl travelControlMock = EasyMock.createMock(TravelControl.class);
    TravelHelper travelHelperMock = EasyMock.createMock(TravelHelper.class);

    @BeforeAll
    public static void setup() {
        empirePlan = new EmpireConfiguration(0, Arrays.asList(new BountyHunters("Tatooine", 0)));
    }

    @BeforeEach
    public void init() {
        control = new OddsControl();
        control.travelControl = travelControlMock;
        control.travelHelper = travelHelperMock;
    }

    // getOdds

    @Test
    public void givenPossibleTravelsWithMinCaptureCounterToOne_WhenGetOdds_ThenResultIs90() {
        List<Travel> travelList = Arrays.asList(TravelMock.Factory.createDefaultTravelMock());
        EasyMock.expect(travelControlMock.getTravels(empirePlan)).andReturn(travelList).once();
        EasyMock.expect(travelHelperMock.getMinCaptureCounter(travelList)).andReturn(1).once();
        EasyMock.replay(travelControlMock, travelHelperMock);

        assertEquals(control.getOdds(empirePlan), 90);

        EasyMock.verify(travelControlMock, travelHelperMock);
    }

    @Test
    public void givenNoPossibleTravel_WhenGetOdds_ThenResultIs0() {
        EasyMock.expect(travelControlMock.getTravels(empirePlan)).andReturn(new ArrayList<>()).once();
        EasyMock.replay(travelControlMock);

        assertEquals(control.getOdds(empirePlan), 0);

        EasyMock.verify(travelControlMock);
    }

    // computeOdds

    @Test
    public void givenCounterToZero_WhenComputeOdds_ThenResultIs100() {
        assertEquals(control.computeOdds(0), 100);
    }

    @Test
    public void givenCounterToOne_WhenComputeOdds_ThenResultIs90() {
        assertEquals(control.computeOdds(1), 90);
    }

    @Test
    public void givenCounterToTwo_WhenComputeOdds_ThenResultIs81() {
        assertEquals(control.computeOdds(2), 81);
    }

    @Test
    public void givenCounterToThree_WhenComputeOdds_ThenResultIs73() {
        assertEquals(control.computeOdds(3), 73);
    }
}
