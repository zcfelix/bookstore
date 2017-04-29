package com.thoughtworks.felix.error;

import java.util.UUID;

public class ErrorBuilder {
    private ErrorBody error;

    public static ErrorBuilder create() {
        return new ErrorBuilder();
    }

    private ErrorBuilder() {
        this.error = new ErrorBody();
    }

    public ErrorBuilder setStatus(String status) {
        error.setStatus(status);
        return this;
    }

    public ErrorBuilder setCode(String code) {
        error.setCode(code);
        return this;
    }

    public ErrorBuilder setDetails(String details) {
        error.setDetails(details);
        return this;
    }

    public ErrorBuilder setTitle(String title) {
        error.setDetails(title);
        return this;
    }

    public ErrorBuilder setPointer(String pointer) {
        error.setPointer(pointer);
        return this;
    }

    public ErrorBuilder setParameter(String parameter) {
        error.setParameter(parameter);
        return this;
    }

    public ErrorBuilder setLinks(Object links) {
        error.setLinks(links);
        return this;
    }

    public ErrorBody build() {
        error.setId(UUID.randomUUID().toString());
        return error;
    }
}
