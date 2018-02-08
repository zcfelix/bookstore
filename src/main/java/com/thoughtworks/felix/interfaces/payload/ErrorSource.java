package com.thoughtworks.felix.interfaces.payload;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorSource {
    private String pointer;
    private String parameter;

    public String getPointer() {
        return pointer;
    }

    public ErrorSource setPointer(String pointer) {
        this.pointer = pointer;
        return this;
    }

    public String getParameter() {
        return parameter;
    }

    public ErrorSource setParameter(String parameter) {
        this.parameter = parameter;
        return this;
    }
}
