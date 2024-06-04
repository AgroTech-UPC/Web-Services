package com.acme.web.services.user.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Fullname(String fullname){
    public Fullname(){this(null);}
}
