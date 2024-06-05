package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.aggregates.Cage;
import com.acme.web.services.management.interfaces.rest.resources.CageResource;

public class CageResourceFromEntityAssembler {
    public static CageResource toResourceFromEntity(Cage entity) {
        return new CageResource(
                entity.getId(),
                entity.name(),
                entity.size(),
                entity.observations(),
                entity.getBreeder().getId());
    }
}
