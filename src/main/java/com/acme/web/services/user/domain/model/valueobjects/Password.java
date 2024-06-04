package com.acme.web.services.user.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;

@Embeddable
public record Password(@Size(min = 8, max = 50) String password) {
    public Password(){this(null);}
}
