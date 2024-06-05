package com.acme.web.services.management.application.internal.commandservices;

import com.acme.web.services.management.domain.model.commands.CreateAnimalCommand;
import com.acme.web.services.management.domain.model.commands.DeleteAnimalCommand;
import com.acme.web.services.management.domain.model.commands.UpdateAnimalCommand;
import com.acme.web.services.management.domain.model.entities.Animal;
import com.acme.web.services.management.domain.model.valueobjects.*;
import com.acme.web.services.management.domain.services.AnimalCommandService;
import com.acme.web.services.management.infrastructure.persitence.jpa.repositories.AnimalRepository;
import com.acme.web.services.management.infrastructure.persitence.jpa.repositories.CageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the AnimalCommandService interface
 */

@Service
public class AnimalCommandServiceImpl implements AnimalCommandService {
    private final AnimalRepository animalRepository;
    private final CageRepository cageRepository;

    public AnimalCommandServiceImpl(AnimalRepository animalRepository, CageRepository cageRepository) {
        this.animalRepository = animalRepository;
        this.cageRepository = cageRepository;
    }

    /**
     * Creates an animal in the database
     * @param command the command to create an animal
     * @return the created animal
     */
    @Override
    public Long handle(CreateAnimalCommand command) {
        var cage = cageRepository.findById(command.cageId());
        if (cage.isEmpty()) {
            throw new IllegalArgumentException("Cage does not exist");
        }
        var animal = new Animal(command, cage.get());
        try {
            animalRepository.save(animal);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving animal: " + e.getMessage());
        }
        return animal.getId();
    }

    /**
     * Updates an animal in the database
     * @param command the command to update an animal
     * @return the updated animal
     */
    @Override
    public Optional<Animal> handle(UpdateAnimalCommand command) {
        return animalRepository.findById(command.animalId()).map(animal -> {
            animal.setName(new Name(command.name()));
            animal.setBreed(Breed.valueOf(command.breed().toUpperCase()));
            animal.setGender(new Gender(command.gender()));
            animal.setBirthdate(new Birthdate(command.birthdate()));
            animal.setWeight(new Weight(command.weight()));
            animal.setIsSick(new IsSick(command.isSick()));
            animal.setObservations(new Observations(command.observations()));
            return animalRepository.save(animal);
        });
    }

    /**
     * Deletes an animal from the database
     * @param command the command to delete an animal
     * @return the deleted animal
     */
    @Override
    public Optional<Animal> handle(DeleteAnimalCommand command) {
        var animal = animalRepository.findById(command.animalId());
        animal.ifPresent(animalRepository::delete);
        return animal;
    }
}
