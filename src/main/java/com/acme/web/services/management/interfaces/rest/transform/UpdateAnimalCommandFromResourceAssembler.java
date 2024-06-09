package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.commands.UpdateAnimalCommand;
import com.acme.web.services.management.interfaces.rest.resources.UpdateAnimalResource;

public class UpdateAnimalCommandFromResourceAssembler {
    public static UpdateAnimalCommand toCommandFromResource(Long animalId, UpdateAnimalResource resource) {
        return new UpdateAnimalCommand(
                animalId, resource.name(), resource.breed(), resource.gender(),
                resource.birthdate(), resource.weight(), resource.isSick(),
                resource.observations(), resource.cageId()
        );
    }
}
