package com.acme.web.services.management.domain.model.valueobjects;

import java.util.Date;

public record DateOfCreation(Date date) {
    public DateOfCreation {
        if (date == null) {
            throw new IllegalArgumentException("Resource date cannot be null");
        }
    }
}
