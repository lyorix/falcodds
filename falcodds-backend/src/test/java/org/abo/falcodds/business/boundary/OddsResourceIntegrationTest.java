package org.abo.falcodds.business.boundary;

import io.quarkus.test.junit.QuarkusTest;
import org.abo.falcodds.business.model.BountyHunters;
import org.abo.falcodds.business.model.EmpireConfiguration;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class OddsResourceIntegrationTest {

    @Test
    public void givenNoEmpirePlan_WhenPostOdds_ThenResponseCodeIs400() {
        given().accept("application/json").contentType("application/json")
                .when().post("/odds")
                .then()
                .statusCode(400);
    }

    @Test
    public void givenEmpirePlanWithoutCountdown_WhenPostOdds_ThenResponseCodeIs400() {
        given().body(new EmpireConfiguration()).accept("application/json").contentType("application/json")
                .when().post("/odds")
                .then()
                .statusCode(400);
    }

    @Test
    public void givenEmpirePlanWithoutBountyHunters_WhenPostOdds_ThenResponseCodeIs400() {
        given().body(new EmpireConfiguration(4, null)).accept("application/json").contentType("application/json")
                .when().post("/odds")
                .then()
                .statusCode(400);
    }

    /**
     * {
     *   "countdown": 7,
     *   "bounty_hunters": [
     *     { "planet": "Hoth", "day": 6 },
     *     { "planet": "Hoth", "day": 7 },
     *     { "planet": "Hoth", "day": 8 }
     *   ]
     * }
     */
    @Test
    public void givenSample1_WhenPostOdds_ThenResultIs0() {
        EmpireConfiguration empirePlan = new EmpireConfiguration(7, Arrays.asList(
                new BountyHunters("Hoth", 6),
                new BountyHunters("Hoth", 7),
                new BountyHunters("Hoth", 8)
        ));
        given().body(empirePlan).accept("application/json").contentType("application/json")
          .when().post("/odds")
          .then()
             .statusCode(200)
             .body(is("0"));
    }

    /**
     * {
     *   "countdown": 8,
     *   "bounty_hunters": [
     *     {"planet": "Hoth", "day": 6 },
     *     {"planet": "Hoth", "day": 7 },
     *     {"planet": "Hoth", "day": 8 }
     *   ]
     * }
     */
    @Test
    public void givenSample2_WhenPostOdds_ThenResultIs81() {
        EmpireConfiguration empirePlan = new EmpireConfiguration(8, Arrays.asList(
                new BountyHunters("Hoth", 6),
                new BountyHunters("Hoth", 7),
                new BountyHunters("Hoth", 8)
        ));
        given().body(empirePlan).accept("application/json").contentType("application/json")
                .when().post("/odds")
                .then()
                .statusCode(200)
                .body(is("81"));
    }

    /**
     * {
     *   "countdown": 9,
     *   "bounty_hunters": [
     *     {"planet": "Hoth", "day": 6 },
     *     {"planet": "Hoth", "day": 7 },
     *     {"planet": "Hoth", "day": 8 }
     *   ]
     * }
     */
    @Test
    public void givenSample3_WhenPostOdds_ThenResultIs90() {
        EmpireConfiguration empirePlan = new EmpireConfiguration(9, Arrays.asList(
                new BountyHunters("Hoth", 6),
                new BountyHunters("Hoth", 7),
                new BountyHunters("Hoth", 8)
        ));
        given().body(empirePlan).accept("application/json").contentType("application/json")
                .when().post("/odds")
                .then()
                .statusCode(200)
                .body(is("90"));
    }

    /**
     * {
     *   "countdown": 10,
     *   "bounty_hunters": [
     *     {"planet": "Hoth", "day": 6 },
     *     {"planet": "Hoth", "day": 7 },
     *     {"planet": "Hoth", "day": 8 }
     *   ]
     * }
     */
    @Test
    public void givenSample4_WhenPostOdds_ThenResultIs100() {
        EmpireConfiguration empirePlan = new EmpireConfiguration(10, Arrays.asList(
                new BountyHunters("Hoth", 6),
                new BountyHunters("Hoth", 7),
                new BountyHunters("Hoth", 8)
        ));
        given().body(empirePlan).accept("application/json").contentType("application/json")
                .when().post("/odds")
                .then()
                .statusCode(200)
                .body(is("100"));
    }

    /**
     * {
     *   "countdown": 8,
     *   "bounty_hunters": []
     * }
     */
    @Test
    public void givenSample5_WhenPostOdds_ThenResultIs100() {
        EmpireConfiguration empirePlan = new EmpireConfiguration(8, new ArrayList<>());
        given().body(empirePlan).accept("application/json").contentType("application/json")
                .when().post("/odds")
                .then()
                .statusCode(200)
                .body(is("100"));
    }
}