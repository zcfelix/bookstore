package com.thoughtworks.felix.domain;

import java.time.Instant;

public class TimeHelper {
    public static String currentTime() {
        return Instant.now().toString();
    }
}
