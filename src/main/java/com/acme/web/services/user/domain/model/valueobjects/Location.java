package com.acme.web.services.user.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Location(String location) {
    public Location(){this(null);}
}
