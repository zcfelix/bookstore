package com.thoughtworks.felix.util;

import java.time.Instant;

public final class Timer {
    public static String currentTime() {
        return Instant.now().toString();
    }
}
