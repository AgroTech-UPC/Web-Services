package com.acme.web.services.management.domain.model.valueobjects;

import java.time.LocalDate;

public record DateOfCreation(LocalDate date) {
    public DateOfCreation {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
    }
}
