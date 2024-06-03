package com.acme.web.services.management.domain.model.valueobjects;

public record Name(String name) {
    public Name {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Resource name cannot be null or blank");
        }
    }
}
