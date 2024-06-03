package com.acme.web.services.user.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Password(String password) {
    public Password(){this(null);}
}
