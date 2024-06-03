package com.acme.web.services.user.interfaces.rest.transform;

import com.acme.web.services.user.domain.model.aggregates.User;
import com.acme.web.services.user.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity){
        return new UserResource(
                entity.getId(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getFullname(),
                entity.getLocation(),
                entity.getBirthdate(),
                entity.getDescription()
        );
    }
}
