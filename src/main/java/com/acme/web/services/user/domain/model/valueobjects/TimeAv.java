package com.acme.web.services.user.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.time.LocalTime;

@Embeddable
public record TimeAv(LocalTime time) {
    public TimeAv(){this(null);}
}
