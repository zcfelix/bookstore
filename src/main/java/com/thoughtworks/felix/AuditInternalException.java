package com.thoughtworks.felix;

import com.thoughtworks.felix.interfaces.payload.ErrorDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public abstract class AuditInternalException extends RuntimeException {
    private List<? extends ErrorDTO> errors;

    public AuditInternalException(List<? extends ErrorDTO> errors) {
        super();
        this.errors = errors;
    }

    public List<? extends ErrorDTO> getErrors() {
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
