package com.example.functionalinterface.validators;

import com.example.functionalinterface.utils.*;

// Custom functional interface example
@FunctionalInterface
public interface Validator<T> {
    boolean validate(T t);

    // Default method - doesn't affect functional interface status
    default ValidationResult validateWithResult(T t) {
        boolean valid = validate(t);
        return new ValidationResult(valid,
                valid ? "Validation passed" : "Validation failed");
    }

    // Static method - doesn't affect functional interface status
    static <T> Validator<T> alwaysValid() {
        return t -> true;
    }
}
