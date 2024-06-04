package com.acme.web.services.user.infrastructure.persistence.jpa.repositories;

import com.acme.web.services.user.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAddress_Email(String email);
    boolean existsByEmailAddress_Email(String email);
}
