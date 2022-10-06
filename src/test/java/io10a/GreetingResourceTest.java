package io10a;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/hello?lang=")
                .then()
                .statusCode(200)
                .body(is("no language selected"));
    }

    @Test
    public void testPlWelcome() {
        given()
                .when().get("/hello?lang=pl")
                .then()
                .statusCode(200)
                .body(is("Dzień dobry"));
    }

    @Test
    public void noLangParam() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(404);
    }


}