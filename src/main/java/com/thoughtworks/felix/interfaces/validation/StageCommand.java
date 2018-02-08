package com.thoughtworks.felix.interfaces.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {StageCommandValidator.class})
public @interface StageCommand {
    String message() default "Invalid command";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean ignoreCase() default true;
}
