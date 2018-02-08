package com.thoughtworks.felix.interfaces.validation;

import com.thoughtworks.felix.domain.change.ChangeStageCommand;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StageCommandValidator implements ConstraintValidator<StageCommand, ChangeStageCommand> {
    private StageCommand annotation;

    @Override
    public void initialize(StageCommand annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(ChangeStageCommand value, ConstraintValidatorContext context) {
        return value != null;
    }
}
