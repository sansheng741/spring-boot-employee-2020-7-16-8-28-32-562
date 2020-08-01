package com.thoughtworks.springbootemployee.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GenderValidator implements ConstraintValidator<GenderValidation, String> {

    String[] genderValues;

    @Override
    public void initialize(GenderValidation constraintAnnotation) {
        constraintAnnotation.message();
        genderValues = constraintAnnotation.genderValues();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.stream(genderValues).collect(Collectors.toList()).contains(value);

    }
}
