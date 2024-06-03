package com.acme.web.services.management.domain.model.valueobjects;

public record Quantity(Integer quantity) {
    public Quantity {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Resource quantity must be a positive integer");
        }
    }
}

