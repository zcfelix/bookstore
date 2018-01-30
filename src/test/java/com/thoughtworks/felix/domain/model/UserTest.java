package com.thoughtworks.felix.domain.model;

import org.junit.Test;

public class UserTest {
    @Test
    public void should() throws Exception {
        User user = new User();
        user.setName("felix");
        user.setAge(26);
        System.out.println(user.getUserRepository());
    }
}
