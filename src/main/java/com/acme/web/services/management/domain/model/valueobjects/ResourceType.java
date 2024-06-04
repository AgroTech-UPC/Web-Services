package com.acme.web.services.management.domain.model.valueobjects;

public record ResourceType(String type) {
    public ResourceType {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Resource type cannot be null or blank");
        }
    }
}
