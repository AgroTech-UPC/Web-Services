package com.acme.web.services.management.application.internal.commandservices;

import com.acme.web.services.management.domain.model.aggregates.Resource;
import com.acme.web.services.management.domain.model.commands.CreateResourceCommand;
import com.acme.web.services.management.domain.model.commands.DeleteResourceCommand;
import com.acme.web.services.management.domain.model.commands.UpdateResourceCommand;
import com.acme.web.services.management.domain.services.ResourceCommandService;
import com.acme.web.services.management.infrastructure.persitence.jpa.repositories.ResourceRepository;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.BreederRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResourceCommandServiceImpl implements ResourceCommandService {
    private final ResourceRepository resourceRepository;
    private final BreederRepository breederRepository;

    public ResourceCommandServiceImpl(ResourceRepository resourceRepository, BreederRepository breederRepository){
        this.resourceRepository = resourceRepository;
        this.breederRepository = breederRepository;
    }

    @Override
    public Long handle(CreateResourceCommand command){
        var breeder = breederRepository.findById(command.breederId());
        if (breeder.isEmpty()){
            throw new IllegalArgumentException("Breeder does not exist");
        }
        var resource = new Resource(command, breeder.get());
        try {
            resourceRepository.save(resource);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving resource: " + e.getMessage());
        }
        return resource.getId();
    }

    @Override
    public Optional<Resource> handle(UpdateResourceCommand command) {
        return Optional.empty();
    }

    @Override
    public Optional<Resource> handle(DeleteResourceCommand command) {
        var resource = resourceRepository.findById(command.resourceId());
        resource.ifPresent(resourceRepository::delete);
        return resource;
    }
}
