package com.acme.web.services.management.interfaces.rest.resources;

import com.acme.web.services.management.domain.model.valueobjects.Name;
import com.acme.web.services.management.domain.model.valueobjects.Size;

public record CageResource(Long id,
                           Name name,
                           Size size,
                           Long breederId) {
}
