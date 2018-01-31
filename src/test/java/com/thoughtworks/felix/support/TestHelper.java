package com.thoughtworks.felix.support;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.thoughtworks.felix.domain.user.User;

import java.io.File;
import java.io.IOException;

public class TestHelper {
    public static User getLegalUser(Long id, String name, Integer age) {
        return User.builder().withId(id).withName(name).withAge(age).build();
    }

    public static String readJsonFrom(String filePath) throws IOException {
        ClassLoader classLoader = TestHelper.class.getClassLoader();
        final File file = new File(classLoader.getResource(filePath).getFile());
        JsonNode jsonNode = JsonLoader.fromFile(file);
        return jsonNode.toString();
    }

    public static User getIllegalUser() {
        return new User();
    }
}
