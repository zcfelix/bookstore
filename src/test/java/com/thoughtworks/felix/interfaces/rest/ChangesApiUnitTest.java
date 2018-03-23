package com.thoughtworks.felix.interfaces.rest;

import com.thoughtworks.felix.support.ApiUnitTest;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.thoughtworks.felix.support.TestHelper.readJsonFrom;
import static com.thoughtworks.felix.support.TestHelper.toJson;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

public class ChangesApiUnitTest extends ApiUnitTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChangesApiUnitTest.class);
    private static final String CHANGE_STAGE_UPDATE_PATH = "/changes/stage";

    @Before
    public void setUp() {
        setUpApi(new ChangesApi());
    }

    @Test
    public void should_400_when_update_change_stage_with_invalid_command() {
        final String json = readJsonFrom("request/update-change-stage-400-invalid-command.json");
        final Map response = given()
                .contentType(JSON)
                .body(json)
                .put(CHANGE_STAGE_UPDATE_PATH)
                .then()
                .statusCode(400)
                .extract()
                .as(Map.class);
        LOGGER.info(">>>>>>>>>>>>>>>" + toJson(response));
    }

    @Test
    public void should_400_when_update_change_stage_with_non_exist_change_id() {
        final String json = readJsonFrom("request/update-change-stage-400-non-exist-id.json");
        final Map response = given()
                .contentType(JSON)
                .body(json)
                .put(CHANGE_STAGE_UPDATE_PATH)
                .then()
                .statusCode(400)
                .extract()
                .as(Map.class);
        LOGGER.info(">>>>>>>>>>>>>>" + toJson(response));
    }

    @Test
    public void should_204_when_update_change_stage_with_valid_command() {
        final String json = readJsonFrom("request/update-change-stage-204.json");
        given()
                .contentType(JSON)
                .body(json)
                .put(CHANGE_STAGE_UPDATE_PATH)
                .then()
                .statusCode(204);
    }
}
