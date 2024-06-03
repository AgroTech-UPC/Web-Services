package com.acme.web.services.user.infrastructure.persistence.jpa.repositories;

import com.acme.web.services.user.domain.model.entities.Breeder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BreederRepository extends JpaRepository<Breeder, Long>{
    Optional<Breeder> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}