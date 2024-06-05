package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.commands.UpdateCageCommand;
import com.acme.web.services.management.interfaces.rest.resources.UpdateCageResource;

public class UpdateCageCommandFromResourceAssembler {
    public static UpdateCageCommand toCommandFromResource(Long cageId, UpdateCageResource resource) {
        return new UpdateCageCommand(
            cageId, resource.name(), resource.size(),resource.observations(), resource.breederId()
        );
    }
}
