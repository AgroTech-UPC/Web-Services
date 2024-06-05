package com.acme.web.services.management.infrastructure.persitence.jpa.repositories;

import com.acme.web.services.management.domain.model.aggregates.Cage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA Repository for Cage entity.
 */
public interface CageRepository extends JpaRepository<Cage, Long> {
    List<Cage> findAllByBreederId(Long breederId);
}
