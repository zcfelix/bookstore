package com.thoughtworks.felix.service;

import com.thoughtworks.felix.domain.User;
import com.thoughtworks.felix.repository.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User expected;
    private User saved;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        expected = User.builder().withName("felix").withAge(26).build();
        saved = User.builder().withName("felix").withAge(26).build();
    }

    @Test
    public void should_save_user_success() throws Exception {
        when(userRepository.saveAndFlush(eq(expected))).thenReturn(saved);
        saved = userService.save(expected);
        assertThat(saved.getName(), is(expected.getName()));
        assertThat(saved.getAge(), is(expected.getAge()));
    }

}