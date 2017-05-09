package com.thoughtworks.felix.resource;

import com.thoughtworks.felix.domain.User;
import com.thoughtworks.felix.ResourceAdvice;
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
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class UserResourceTest extends ResourceSupport {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserResource userResource;

    private User user, illegalUser;

    @Before
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(
                MockMvcBuilders.standaloneSetup(userResource)
                        .setControllerAdvice(new ResourceAdvice())
//                        .setHandlerExceptionResolvers(getGlobalExceptionHandlerResolver())
        );
        user = TestHelper.getLegalUser(1L, "Felix", 26);
        illegalUser = TestHelper.getIllegalUser();
        when(userService.save(any(User.class))).thenReturn(user);
    }

    @Test
    public void should_return_201_when_create_user_success() throws Exception {
        given().contentType("application/json")
                .body(user)
                .when().post("/users")
                .then()
                .contentType("application/json")
                .statusCode(201)
                .body("age", equalTo(26));
    }

    @Test
    public void should_return_400_when_create_user_with_illegal_user() throws Exception {
        given().contentType("application/json")
                .body(illegalUser)
                .when().post("/users")
                .then()
                .contentType("application/json")
                .statusCode(400);
    }
}