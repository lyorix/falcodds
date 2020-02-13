package org.abo.falcodds.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OddsHelperTest {

    OddsHelper helper;

    @BeforeEach
    public void init() {
        helper = new OddsHelper();
    }

    // computeOdds

    @Test
    public void givenCaptureCounterToZero_WhenComputeOdds_ThenResultIsHundred() {
        assertEquals(helper.computeOdds(0), 100);
    }

    @Test
    public void givenCaptureCounterToOne_WhenComputeOdds_ThenResultIsNinety() {
        assertEquals(helper.computeOdds(1), 90);
    }

    @Test
    public void givenCaptureCounterToTwo_WhenComputeOdds_ThenResultIsEightyOne() {
        assertEquals(helper.computeOdds(2), 81);
    }

    @Test
    public void givenCaptureCounterToThree_WhenComputeOdds_ThenResultIsSeventyThree() {
        assertEquals(helper.computeOdds(3), 73);
    }
}
