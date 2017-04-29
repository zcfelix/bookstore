package com.thoughtworks.felix.error;

import java.util.List;

public class ErrorResponse {
    private List<ErrorBody> errors;
    private Object meta;

    public ErrorResponse(List<ErrorBody> errors, Object meta) {
        this.errors = errors;
        this.meta = meta;
    }

    public List<ErrorBody> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorBody> errors) {
        this.errors = errors;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }
}
