package org.abo.falcodds.business.boundary;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PingResourceIntegrationTest {

    @Test
    public void testPing() {
        given()
          .when().get("/ping")
          .then()
             .statusCode(200)
             .body(is("falcodds"));
    }

}