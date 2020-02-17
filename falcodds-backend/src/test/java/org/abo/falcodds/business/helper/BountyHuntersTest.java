package org.abo.falcodds.business.helper;

import org.abo.falcodds.business.model.BountyHunters;
import org.abo.falcodds.business.model.EmpireConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BountyHuntersTest {

    private static EmpireConfiguration empirePlan;

    BountyHuntersHelper helper;

    @BeforeAll
    public static void setup() {
        empirePlan = new EmpireConfiguration(0, Arrays.asList(new BountyHunters("Tatooine", 0)));
    }

    @BeforeEach
    public void init() {
        helper = new BountyHuntersHelper();
    }

    // hasBountyHunters

    @Test
    public void givenEmpirePlan_WhenHasBountyHuntersOnDayZeroOnTatooine_ThenResultIsTrue() {
        assertTrue(helper.hasBountyHunters(empirePlan, "Tatooine", 0));
    }

    @Test
    public void givenEmpirePlan_WhenHasBountyHuntersOnDayOneOnTatooine_ThenResultIsFalse() {
        assertFalse(helper.hasBountyHunters(empirePlan, "Tatooine", 1));
    }

    @Test
    public void givenEmpirePlan_WhenHasBountyHuntersOnDayZeroOnDagobah_ThenResultIsFalse() {
        assertFalse(helper.hasBountyHunters(empirePlan, "Dagobah", 0));
    }
}
