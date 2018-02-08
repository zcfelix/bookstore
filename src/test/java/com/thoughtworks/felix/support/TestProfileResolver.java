package com.thoughtworks.felix.support;

import org.springframework.test.context.ActiveProfilesResolver;

public class TestProfileResolver implements ActiveProfilesResolver {
    @Override
    public String[] resolve(Class<?> testClass) {
        return new String[]{"dev"};
    }
}
