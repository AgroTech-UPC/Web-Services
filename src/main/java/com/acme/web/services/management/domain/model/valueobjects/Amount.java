package com.acme.web.services.management.domain.model.valueobjects;

public record Amount(Float amount) {
    public Amount {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Amount must be a positive number");
        }
    }
}
