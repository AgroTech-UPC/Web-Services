package com.acme.web.services.user.interfaces.rest.transform;

import com.acme.web.services.user.domain.model.entities.Advisor;
import com.acme.web.services.user.interfaces.rest.resources.AdvisorResource;

public class AdvisorResourceFromEntityAssembler {
    public static AdvisorResource toResourceFromEntity(Advisor entity){
        return new AdvisorResource(
                entity.getId(),
                entity.getOccupation(),
                entity.getExperience(),
                entity.getPhoto(),
                entity.getRating(),
                entity.getUser().getId()
        );
    }
}
