package com.thoughtworks.felix.domain.user;

import com.thoughtworks.felix.BookstoreApplication;
import com.thoughtworks.felix.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserRepoTest {

    @Autowired
    UserRepository userRepository;
    @Test
    public void should_save_and_flush() {
        userRepository.saveAndFlush(User.builder().withId(1L).withName("felix").withAge(2).build());
        final User one = userRepository.findOne(1L);
        System.out.println(one);
    }

    @Test
    public void should_save() {
        final User saved = userRepository.save(TestHelper.getLegalUser(4L, "kitty", 28));
        System.out.println(saved);
    }
}
