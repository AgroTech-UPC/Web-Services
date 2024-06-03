package com.acme.web.services.user.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;

@Embeddable
public record EmailAddress(@Email String email) {
    public EmailAddress() {
        this(null);
    }
}
