package com.acme.web.services.management.domain.model.valueobjects;

public record Weight(Float weight) {
    public Weight {
        if (weight == null || weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative or null");
        }
    }
}
