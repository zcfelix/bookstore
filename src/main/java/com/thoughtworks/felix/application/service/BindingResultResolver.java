package com.thoughtworks.felix.application.service;

import com.thoughtworks.felix.interfaces.payload.ErrorDTO;
import org.springframework.validation.BindingResult;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BindingResultResolver {
    public static List<ErrorDTO> parseErrors(BindingResult result) {
        return result.getFieldErrors()
                .stream()
                .map(e -> new ErrorDTO()
                        .setCode("INVALID-FIELD")
                        .setStatus("400")
                        .setTitle(e.getDefaultMessage())
                        .setDetail(String.format("%s: %s", e.getDefaultMessage(), e.getRejectedValue()))
                        .setPointer(e.getField()))
                .collect(toList());
    }
}
