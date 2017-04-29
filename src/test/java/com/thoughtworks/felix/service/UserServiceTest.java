package com.thoughtworks.felix.service;

import com.thoughtworks.felix.BookstoreApplication;
import com.thoughtworks.felix.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private User expected;
    private User saved;


    @Before
    public void setUp() throws Exception {
        expected = User.builder().withName("felix").withAge(26).build();
    }

    @Test
    public void should_save_user_success() throws Exception {
        saved = userService.save(expected);
        assertThat(saved.getName(), is(expected.getName()));
        assertThat(saved.getAge(), is(expected.getAge()));
    }

    @After
    public void tearDown() throws Exception {
        userService.delete(saved.getId());
    }

}