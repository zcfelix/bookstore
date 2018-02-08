package com.thoughtworks.felix;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus status();
    String code();
    String title();
}
