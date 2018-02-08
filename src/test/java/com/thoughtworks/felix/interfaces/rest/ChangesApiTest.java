package com.thoughtworks.felix.interfaces.rest;

import com.thoughtworks.felix.support.ApiTest;
import org.junit.Test;

import static com.thoughtworks.felix.support.TestHelper.readJsonFrom;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ChangesApiTest extends ApiTest{

    @Test
    public void should_204_when_update_change_stage_with_valid_command() {
        final String json = readJsonFrom("request/update-change-stage-204.json");
        given()
                .contentType(JSON)
                .body(json)
                .put("/changes/stage")
                .then()
                .statusCode(204);
    }
}
