package com.thoughtworks.felix;

import com.thoughtworks.felix.interfaces.payload.BatchErrorPayload;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(value = AuditInternalException.class)
    public ResponseEntity<BatchErrorPayload> auditInternalExceptionHandler(AuditInternalException exception) {
        return ResponseEntity.status(exception.httpStatus()).body(BatchErrorPayload.withErrors(exception.getErrors()));
    }

}
