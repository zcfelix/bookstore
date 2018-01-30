package com.thoughtworks.felix.interfaces.rest;

import com.thoughtworks.felix.domain.user.User;
import com.thoughtworks.felix.domain.user.UserService;
import com.thoughtworks.felix.support.ApiUnitTest;
import com.thoughtworks.felix.support.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class UsersApiTest extends ApiUnitTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UsersApi usersApi;

    private User user, illegalUser;

    @Before
    public void setUp() {
        setUpApi(usersApi);
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