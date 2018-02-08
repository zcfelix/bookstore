package com.thoughtworks.felix.interfaces.rest;

import com.thoughtworks.felix.support.ApiUnitTest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.thoughtworks.felix.support.TestHelper.readJsonFrom;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

public class ChangesApiUnitTest extends ApiUnitTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangesApiUnitTest.class);

    @Before
    public void setUp() {
        setUpApi(new ChangesApi());
    }

    @Test
    public void should_400_when_update_change_stage_with_invalid_command() {
        final String json = readJsonFrom("request/update-change-stage-400-invalid-command.json");
        given()
                .contentType(JSON)
                .body(json)
                .put("/changes/stage")
                .then()
                .statusCode(400);
    }

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
