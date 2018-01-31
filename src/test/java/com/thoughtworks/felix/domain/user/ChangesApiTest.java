package com.thoughtworks.felix.domain.user;


import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static com.thoughtworks.felix.support.TestHelper.readJsonFrom;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ChangesApiTest {
    @Before
    public void setUp() throws Exception {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;
    }

    @Test
    public void should_read_simple_json_as_object() throws IOException {
        final String json = readJsonFrom("request/update-change-simple.json");
        given()
                .contentType(JSON)
                .body(json)
                .put("/changes/stage")
                .then()
                .statusCode(204);
    }

    @Test
    public void should_read_json_as_objects_array() throws IOException {
        final String json = readJsonFrom("request/update-change.json");
        given()
                .contentType(JSON)
                .body(json)
                .put("changes/stage")
                .then()
                .statusCode(204);
    }
}
