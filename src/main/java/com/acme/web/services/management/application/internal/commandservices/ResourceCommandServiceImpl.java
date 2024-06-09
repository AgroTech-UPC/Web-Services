package com.acme.web.services.management.application.internal.commandservices;

import com.acme.web.services.management.domain.model.aggregates.Resource;
import com.acme.web.services.management.domain.model.commands.CreateResourceCommand;
import com.acme.web.services.management.domain.model.commands.DeleteResourceCommand;
import com.acme.web.services.management.domain.model.commands.UpdateResourceCommand;
import com.acme.web.services.management.domain.model.valueobjects.*;
import com.acme.web.services.management.domain.services.ResourceCommandService;
import com.acme.web.services.management.infrastructure.persitence.jpa.repositories.ResourceRepository;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.BreederRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the ResourceCommandService interface
 */
@Service
public class ResourceCommandServiceImpl implements ResourceCommandService {
    private final ResourceRepository resourceRepository;
    private final BreederRepository breederRepository;

    public ResourceCommandServiceImpl(ResourceRepository resourceRepository, BreederRepository breederRepository){
        this.resourceRepository = resourceRepository;
        this.breederRepository = breederRepository;
    }

    /**
     * Creates a resource in the database
     * @param command the command to create a resource
     * @return the created resource
     */

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

    /**
     * Updates a resource in the database
     * @param command the command to update a resource
     * @return the updated resource
     */
    @Override
    public Optional<Resource> handle(UpdateResourceCommand command) {
        return resourceRepository.findById(command.resourceId()).map(resource -> {
            resource.setName(new Name(command.name()));
            resource.setResourceType(ResourceType.valueOf(command.type().toUpperCase()));
            resource.setQuantity(new Quantity(command.quantity()));
            resource.setDate(new DateOfCreation(command.date()));
            resource.setObservations(new Observations(command.observations()));
            return resourceRepository.save(resource);
        });
    }

    /**
     * Deletes a resource in the database
     * @param command the command to delete a resource
     * @return the deleted resource
     */
    @Override
    public Optional<Resource> handle(DeleteResourceCommand command) {
        if (!resourceRepository.existsById(command.resourceId())) {
            throw new IllegalArgumentException("Resource does not exist");
        }
        var resource = resourceRepository.findById(command.resourceId());
        resource.ifPresent(resourceRepository::delete);
        return resource;
    }
}
