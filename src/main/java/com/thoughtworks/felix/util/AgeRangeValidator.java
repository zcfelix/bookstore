package com.thoughtworks.felix.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeRangeValidator implements ConstraintValidator<InRange, Integer>{

    private int min;
    private int max;

    @Override
    public void initialize(InRange constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value == null || (value >= min && value <= max);
    }
}
