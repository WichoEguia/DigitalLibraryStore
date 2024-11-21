package com.example.DigitalLibraryStore.utils.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MinPagesValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface MinPages {
    int value() default 100;

    String message() default "El libro debe tener al menos {value} paginas.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
