package com.acme.web.services.user.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

@Embeddable
public record Birthdate(@Past LocalDate birthdate) {
    public Birthdate(){this(null);}
}
