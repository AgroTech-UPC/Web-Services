package com.acme.web.services.appointment.domain.model.valueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Status(String status) {
    public Status(){this(null);}
}
