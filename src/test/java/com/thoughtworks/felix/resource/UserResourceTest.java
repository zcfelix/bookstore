package com.thoughtworks.felix.resource;

import com.thoughtworks.felix.domain.User;
import com.thoughtworks.felix.service.UserService;
import com.thoughtworks.felix.util.ResourceSupport;
import com.thoughtworks.felix.util.TestHelper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


public class UserResourceTest extends ResourceSupport {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserResource userResource;

    private User validUser, invalidUser;

    @Before
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(
                MockMvcBuilders.standaloneSetup(userResource)
                        .setHandlerExceptionResolvers(getGlobalExceptionHandlerResolver())
        );
        validUser = TestHelper.getValidUser("Felix", 26);
        invalidUser = TestHelper.getInvalidUser();
        when(userService.save(any(User.class))).thenReturn(validUser);
    }

    @Test
    public void should_create_user_success() throws Exception {
        given().contentType("application/json")
                .body(validUser)
                .when().post("/users")
                .then()
                .statusCode(201);
    }

    @Test
    public void should_return_400_when_name_is_illegal() throws Exception {
        given().contentType("application/json")
                .body(invalidUser)
                .when().post("/users")
                .then()
                .statusCode(400);
    }
}