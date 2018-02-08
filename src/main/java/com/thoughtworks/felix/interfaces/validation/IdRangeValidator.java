package com.thoughtworks.felix.interfaces.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdRangeValidator implements ConstraintValidator<IdRange, Integer>{
    private int min;
    private int max;

    @Override
    public void initialize(IdRange constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value == null || (value >= min && value <= max);
    }
}
