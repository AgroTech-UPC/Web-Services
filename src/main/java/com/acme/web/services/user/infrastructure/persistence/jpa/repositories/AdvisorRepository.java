package com.acme.web.services.user.infrastructure.persistence.jpa.repositories;

import com.acme.web.services.user.domain.model.aggregates.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
    Optional<Advisor> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
