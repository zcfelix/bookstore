package com.thoughtworks.felix;

import com.thoughtworks.felix.error.ErrorBuilder;
import com.thoughtworks.felix.error.ErrorResponse;
import com.thoughtworks.felix.exception.RequestInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResourceAdvice {

    @ExceptionHandler(value = RequestInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleRequestInvalidException(RequestInvalidException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        return new ErrorResponse(
                errors.stream().map(error -> ErrorBuilder.create().setStatus(HttpStatus.BAD_REQUEST.toString())
                .setCode("INPUT_REQUEST_VALID")
                .setTitle(extractStr(error.getDefaultMessage(), "title"))
                .setPointer(extractStr(error.getDefaultMessage(), "pointer"))
                .setDetails(extractStr(error.getDefaultMessage(), "details"))
                .setLinks(e.getLinks())
                .setCode(error.getCode()).build()).collect(Collectors.toList()), null
        );
    }

    private String extractStr(String message, String k) {
        return substringBetween(message, k + "={", "}");
    }

    private String substringBetween(String str, String open, String close) {
        if (str != null && open != null && close != null) {
            int start = str.indexOf(open);
            if (start != -1) {
                int end = str.indexOf(close, start + open.length());
                if (end != -1) {
                    return str.substring(start + open.length(), end);
                }
            }
            return null;
        } else {
            return null;
        }
    }
}
