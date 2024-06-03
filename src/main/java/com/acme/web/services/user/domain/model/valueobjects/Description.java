package com.acme.web.services.user.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Description(String description) {
    public Description(){this(null);}
}
