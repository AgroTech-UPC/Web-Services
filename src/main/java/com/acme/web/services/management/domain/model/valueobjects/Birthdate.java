package com.acme.web.services.management.domain.model.valueobjects;

import java.time.LocalDate;

public record Birthdate(LocalDate birthdate) {
    public Birthdate {
        if (birthdate == null) {
            throw new IllegalArgumentException("Birthdate cannot be null");
        }
    }
}
