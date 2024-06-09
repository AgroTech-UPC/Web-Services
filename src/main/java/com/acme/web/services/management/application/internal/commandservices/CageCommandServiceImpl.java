package com.acme.web.services.management.application.internal.commandservices;

import com.acme.web.services.management.domain.model.aggregates.Cage;
import com.acme.web.services.management.domain.model.commands.CreateCageCommand;
import com.acme.web.services.management.domain.model.commands.DeleteCageCommand;
import com.acme.web.services.management.domain.model.commands.UpdateCageCommand;
import com.acme.web.services.management.domain.model.valueobjects.Name;
import com.acme.web.services.management.domain.model.valueobjects.Observations;
import com.acme.web.services.management.domain.model.valueobjects.Size;
import com.acme.web.services.management.domain.services.CageCommandService;
import com.acme.web.services.management.infrastructure.persitence.jpa.repositories.CageRepository;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.BreederRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the CageCommandService interface
 */
@Service
public class CageCommandServiceImpl implements CageCommandService {
    private final CageRepository cageRepository;
    private final BreederRepository breederRepository;

    public CageCommandServiceImpl(CageRepository cageRepository, BreederRepository breederRepository) {
        this.cageRepository = cageRepository;
        this.breederRepository = breederRepository;
    }

    /**
     * Creates a cage in the database
     * @param command the command to create a cage
     * @return the created cage
     */
    @Override
    public Long handle(CreateCageCommand command) {
        var breeder = breederRepository.findById(command.breederId());
        if (breeder.isEmpty()) {
            throw new IllegalArgumentException("Breeder does not exist");
        }

        var cage = new Cage(command.name(), command.size(), command.observations(), breeder.get());

        try {
            cageRepository.save(cage);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving cage: " + e.getMessage());
        }
        return cage.getId();
    }

    /**
     * Updates a cage in the database
     * @param command the command to update a cage
     * @return the updated cage
     */
    @Override
    public Optional<Cage> handle(UpdateCageCommand command) {
        return cageRepository.findById(command.cageId()).map(cage -> {
            cage.setName(new Name(command.name()));
            cage.setSize(new Size(command.size()));
            cage.setObservations(new Observations(command.observations()));
            return cageRepository.save(cage);
        });
    }

    /**
     * Deletes a cage from the database
     * @param command the command to delete a cage
     * @return the deleted cage
     */
    @Override
    public Optional<Cage> handle(DeleteCageCommand command) {
        if(!cageRepository.existsById(command.cageId())){
            throw new IllegalArgumentException("Cage does not exist");
        }
        var cage = cageRepository.findById(command.cageId());
        cage.ifPresent(cageRepository::delete);
        return cage;
    }
}
