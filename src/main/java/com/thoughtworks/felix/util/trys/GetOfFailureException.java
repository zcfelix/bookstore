package com.thoughtworks.felix.util.trys;

public class GetOfFailureException extends RuntimeException {
    private static final String MESSAGE = "get of a Failure object";

    public GetOfFailureException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
