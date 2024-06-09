package com.acme.web.services.user.interfaces.rest.transform;

import com.acme.web.services.user.domain.model.commands.CreateUserCommand;
import com.acme.web.services.user.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource){
        return new CreateUserCommand(
                resource.email(),
                resource.password(),
                resource.fullname(),
                resource.location(),
                resource.birthdate(),
                resource.description()
        );
    }
}
