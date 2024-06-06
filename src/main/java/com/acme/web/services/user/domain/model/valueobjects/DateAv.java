package com.acme.web.services.user.domain.model.valueobjects;


import jakarta.persistence.Embeddable;

import java.util.Date;

@Embeddable
public record DateAv(Date date) {
    public DateAv(){this(null);}
}
