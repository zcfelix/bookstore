package com.thoughtworks.felix.support;

import com.thoughtworks.felix.BookstoreApplication;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class,
        webEnvironment = DEFINED_PORT)
@ActiveProfiles(resolver = TestProfileResolver.class)
public abstract class ApiTest {
    @BeforeClass
    public static void beforeClass() {
        System.out.println(String.format("Api Tests resolved profiles are [%s].", System.getProperty("spring.profiles.active")));
        RestAssured.port = 8081;
        RestAssured.baseURI = "http://localhost";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
//        RestAssured.basePath = "/gdata";
    }
}
