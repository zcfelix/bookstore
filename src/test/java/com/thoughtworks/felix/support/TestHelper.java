package com.thoughtworks.felix.support;

import com.thoughtworks.felix.domain.user.User;

public class TestHelper {
    public static User getLegalUser(Long id, String name, Integer age) {
        return User.builder().withId(id).withName(name).withAge(age).build();
    }

    public static User getIllegalUser() {
        return new User();
    }
}