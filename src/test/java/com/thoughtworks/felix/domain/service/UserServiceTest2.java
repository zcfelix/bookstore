package com.thoughtworks.felix.domain.service;

import com.thoughtworks.felix.domain.model.User;
import com.thoughtworks.felix.domain.service.UserService;
import com.thoughtworks.felix.domain.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserServiceTest2 {

    private User expected;

    @Autowired
    private UserRepository userRepository;

    private UserService userService;


    @Before
    public void setUp() throws Exception {
        userService = new UserService(userRepository);
        expected = User.builder().withName("felix").withAge(26).build();
    }

    @Test
    public void should_save_user_success() throws Exception {
//        User saved = userRepository.saveAndFlush(expected);
        User saved = userService.save(expected);
        assertThat(saved.getName(), is(expected.getName()));
        assertThat(saved.getAge(), is(expected.getAge()));
    }
}
