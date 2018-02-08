package com.thoughtworks.felix;

import com.thoughtworks.felix.interfaces.payload.Error;
import org.springframework.http.HttpStatus;

import java.util.List;

public abstract class AuditInternalException extends RuntimeException {
    private List<? extends Error> errors;

    public AuditInternalException(List<? extends Error> errors) {
        super();
        this.errors = errors;
    }

    public List<? extends Error> getErrors() {
        return errors;
    }

    public abstract HttpStatus httpStatus();

    public boolean is4xxFailure() {
        return httpStatus().is4xxClientError();
    }

    public boolean is5xxFailure() {
        return httpStatus().is5xxServerError();
    }
}
