package com.thoughtworks.springbootemployee.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
public @interface GenderValidation {
    String message() default "";
    Class<?>[] groups() default {};
    String[] genderValues() default {};
    Class<? extends Payload>[] payload() default {};
}
