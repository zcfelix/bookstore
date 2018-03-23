package com.thoughtworks.felix.interfaces.rest;

import com.thoughtworks.felix.AuditInternalException;
import com.thoughtworks.felix.interfaces.payload.ErrorDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public class BadRequestException extends AuditInternalException {
    public BadRequestException(List<? extends ErrorDTO> errors) {
        super(errors);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
