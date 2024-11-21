package com.example.DigitalLibraryStore.utils.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinPagesValidator implements ConstraintValidator<MinPages, Integer> {
    private int minValue;

    @Override
    public void initialize(MinPages constraintAnnotation) {
        this.minValue = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value >= minValue;
    }
}
