package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.commands.UpdateResourceCommand;
import com.acme.web.services.management.interfaces.rest.resources.UpdateResourceResource;

public class UpdateResourceCommandFromResourceAssembler {
    public static UpdateResourceCommand toCommandFromResource(Long resourceId, UpdateResourceResource resource) {
        return new UpdateResourceCommand(
            resourceId, resource.name(), resource.type(), resource.quantity(), resource.date(), resource.observations(), resource.breederId()
        );
    }
}
