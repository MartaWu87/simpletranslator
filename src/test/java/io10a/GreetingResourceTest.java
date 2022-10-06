package io10a;


import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {


    @Test
    public void testHello() {
        given()
                .when().get("/hello/{name}", "pl")
                .then()
                .statusCode(200)
                .body(is("Dzień dobry"));
    }

    @Test
    public void testHelloTwo() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(405);
    }

    @Test
    public void testHelloCode() {
        Language language = new Language("pl", "Dzień dobry");
        given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(language)
                .post("/hello/{name}", "pl")
                .then()
                .statusCode(200);
    }

    @Test
    public void testHelloEdit() {
        Language language = new Language("pl", "Hejoo");
        given()
                .when()
                .contentType(MediaType.APPLICATION_JSON)
                .body(language)
                .post("/hello/{name}", "pl")
                .then()
                .statusCode(200);
    }

    @Test
    public void testHelloRemove() {
        given()
                .when().get("/hello/{name}", "pl")
                .then()
                .statusCode(204);
    }
}