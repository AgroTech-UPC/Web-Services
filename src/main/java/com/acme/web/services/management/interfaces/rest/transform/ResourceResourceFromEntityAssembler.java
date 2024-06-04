package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.aggregates.Resource;
import com.acme.web.services.management.interfaces.rest.resources.ResourceResource;

public class ResourceResourceFromEntityAssembler {
    public static ResourceResource toResourceFromEntity(Resource entity) {
        return new ResourceResource(
                entity.getId(),
                entity.getName(),
                entity.getType(),
                entity.getQuantity(),
                entity.getDate(),
                entity.getObservations(),
                entity.getBreeder().getId());
    }
}
