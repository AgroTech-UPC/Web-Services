package com.acme.web.services.management.domain.model.valueobjects;

public record Observations(String observations) {
    public Observations {
        if (observations == null) {
            throw new IllegalArgumentException("Observations cannot be null");
        }
    }
}

