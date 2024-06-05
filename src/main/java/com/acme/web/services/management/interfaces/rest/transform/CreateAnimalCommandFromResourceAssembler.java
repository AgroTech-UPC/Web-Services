package com.acme.web.services.management.interfaces.rest.transform;

import com.acme.web.services.management.domain.model.commands.CreateAnimalCommand;
import com.acme.web.services.management.interfaces.rest.resources.CreateAnimalResource;

public class CreateAnimalCommandFromResourceAssembler {
    public static CreateAnimalCommand toCommandFromResource(CreateAnimalResource resource){
        return new CreateAnimalCommand(
                resource.name(),
                resource.breed(),
                resource.gender(),
                resource.birthdate(),
                resource.weight(),
                resource.isSick(),
                resource.observations(),
                resource.cageId()
        );
    }
}
