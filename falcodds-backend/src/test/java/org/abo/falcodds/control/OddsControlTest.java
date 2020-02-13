package org.abo.falcodds.control;

import org.abo.falcodds.helper.OddsHelper;
import org.abo.falcodds.helper.TravelHelper;
import org.abo.falcodds.mocks.TravelMock;
import org.abo.falcodds.model.BountyHunters;
import org.abo.falcodds.model.EmpireConfiguration;
import org.abo.falcodds.model.Travel;
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
    OddsHelper oddsHelperMock = EasyMock.createMock(OddsHelper.class);
    TravelHelper travelHelperMock = EasyMock.createMock(TravelHelper.class);

    @BeforeAll
    public static void setup() {
        empirePlan = new EmpireConfiguration(0, Arrays.asList(new BountyHunters("Tatooine", 0)));
    }

    @BeforeEach
    public void init() {
        control = new OddsControl();
        control.travelControl = travelControlMock;
        control.oddsHelper = oddsHelperMock;
        control.travelHelper = travelHelperMock;
    }

    // getOdds

    @Test
    public void givenTravels_WhenGetOdds_ThenResultIsValid() {
        List<Travel> travelList = Arrays.asList(TravelMock.Factory.createDefaultTravelMock());
        EasyMock.expect(travelControlMock.getTravels(empirePlan)).andReturn(travelList).once();
        EasyMock.expect(travelHelperMock.getMinCaptureCounter(travelList)).andReturn(1).once();
        EasyMock.expect(oddsHelperMock.computeOdds(1)).andReturn(90L).once();
        EasyMock.replay(travelControlMock, travelHelperMock, oddsHelperMock);

        assertEquals(control.getOdds(empirePlan), 90);

        EasyMock.verify(travelControlMock, travelHelperMock, oddsHelperMock);
    }

    @Test
    public void givenNoTravel_WhenGetOdds_ThenResultIsZero() {
        EasyMock.expect(travelControlMock.getTravels(empirePlan)).andReturn(new ArrayList<>()).once();
        EasyMock.replay(travelControlMock);

        assertEquals(control.getOdds(empirePlan), 0);

        EasyMock.verify(travelControlMock);
    }
}
