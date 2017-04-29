package com.thoughtworks.felix.util;

import com.thoughtworks.felix.domain.User;

public class TestHelper {
    public static User getValidUser(String name, Integer age) {
        return User.builder().withName(name).withAge(age).build();
    }

    public static User getInvalidUser() {
        return new User();
    }
}
