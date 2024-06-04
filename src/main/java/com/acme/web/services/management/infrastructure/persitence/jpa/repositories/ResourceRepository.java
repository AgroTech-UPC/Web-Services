package com.acme.web.services.management.infrastructure.persitence.jpa.repositories;

import com.acme.web.services.management.domain.model.aggregates.Resource;
import com.acme.web.services.management.domain.model.valueobjects.Name;
import com.acme.web.services.management.domain.model.valueobjects.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
    Optional<Resource> findByBreederId(Long breederId);
    boolean existsByBreederId(Long breederId);
    List<Resource> findAllByResourceTypeAndBreederId(ResourceType type, Long breederId);
}
