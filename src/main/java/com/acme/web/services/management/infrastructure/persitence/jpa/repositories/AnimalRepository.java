package com.acme.web.services.management.infrastructure.persitence.jpa.repositories;

import com.acme.web.services.management.domain.model.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for the Animal entity.
 */
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findAllByCageId(Long cageId);
}
