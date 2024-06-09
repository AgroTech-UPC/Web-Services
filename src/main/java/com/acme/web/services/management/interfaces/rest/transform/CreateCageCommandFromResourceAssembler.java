package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.commands.CreateCageCommand;
import com.acme.web.services.management.interfaces.rest.resources.CreateCageResource;

public class CreateCageCommandFromResourceAssembler {
    public static CreateCageCommand toCommandFromResource(CreateCageResource resource){
        return new CreateCageCommand(
                resource.name(),
                resource.size(),
                resource.observations(),
                resource.breederId()
        );
    }
}
