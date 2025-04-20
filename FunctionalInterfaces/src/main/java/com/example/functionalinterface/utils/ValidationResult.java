package com.example.functionalinterface.utils;

public class ValidationResult {
    private final boolean valid;
    private final String message;

    public ValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    @Override
    public String toString() {
        return valid ? "✓ " + message : "✗ " + message;
    }
}