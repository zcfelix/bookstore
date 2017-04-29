package com.thoughtworks.felix.exception;

import org.springframework.hateoas.Link;
import org.springframework.validation.BindingResult;

public class RequestInvalidException extends RuntimeException {
    private Link[] links;
    private BindingResult bindingResult;

    public RequestInvalidException(BindingResult bindingResult, Link... links) {
        this.bindingResult = bindingResult;
        this.links = links;
    }

    public RequestInvalidException(String message) {
        super(message);
    }

    public Link[] getLinks() {
        return links;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }
}
