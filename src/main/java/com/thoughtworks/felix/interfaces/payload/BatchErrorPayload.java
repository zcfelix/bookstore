package com.thoughtworks.felix.interfaces.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BatchErrorPayload {
    @JsonProperty("errors")
    private List<? extends Error> errors;

    public void setErrors(List<? extends Error> errors) {
        this.errors = errors;
    }

    public static BatchErrorPayload withErrors(List<? extends Error> errors) {
        final BatchErrorPayload payload = new BatchErrorPayload();
        payload.setErrors(errors);
        return payload;
    }
}
