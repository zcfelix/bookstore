package com.thoughtworks.felix.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.thoughtworks.felix.domain.user.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class TestHelper {
    public static User getLegalUser(Long id, String name, Integer age) {
        return User.builder().withId(id).withName(name).withAge(age).build();
    }

    public static String readJsonFrom(String filePath) {
        final URL url = TestHelper.class.getClassLoader().getResource(filePath);
        if (url == null) {
            throw new RuntimeException(String.format("Can not find the file path: %s", filePath));
        }
        final File file = new File(url.getFile());
        JsonNode jsonNode = null;
        try {
            jsonNode = JsonLoader.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonNode.toString();
    }

    public static User getIllegalUser() {
        return new User();
    }

    public static String toJson(Map map) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
