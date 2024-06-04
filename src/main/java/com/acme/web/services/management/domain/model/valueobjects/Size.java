package com.acme.web.services.management.domain.model.valueobjects;

public record Size(int size) {
    public Size {
        if (size <= 0) {
            throw new IllegalArgumentException("Resource size must be greater than zero");
        }
    }
}
