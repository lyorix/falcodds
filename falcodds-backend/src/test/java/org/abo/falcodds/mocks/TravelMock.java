package org.abo.falcodds.mocks;

import org.abo.falcodds.business.model.Travel;

import java.util.Arrays;

public class TravelMock {

    public static class Factory {

        public static Travel createDefaultTravelMock() {
            return TravelMock.Factory.createDefaultTravelMock(0, 0);
        }

        public static Travel createDefaultTravelMock(Integer captureCounter, Integer remainingAutonomy) {
            return new Travel(Arrays.asList(TravelStepMock.DAGOBAH_STEP, TravelStepMock.HOTH_STEP), remainingAutonomy, captureCounter);
        }
    }
}
