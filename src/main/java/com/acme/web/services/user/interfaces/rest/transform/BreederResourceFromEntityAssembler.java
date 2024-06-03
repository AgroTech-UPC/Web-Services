package com.acme.web.services.user.interfaces.rest.transform;

import com.acme.web.services.user.domain.model.entities.Breeder;
import com.acme.web.services.user.interfaces.rest.resources.BreederResource;

public class BreederResourceFromEntityAssembler {
    public static BreederResource toResourceFromEntity(Breeder entity){
        return new BreederResource(
                entity.getId(),
                entity.getUser().getId()
        );
    }
}
