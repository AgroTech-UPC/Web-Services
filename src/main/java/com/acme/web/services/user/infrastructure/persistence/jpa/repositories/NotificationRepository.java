package com.acme.web.services.user.infrastructure.persistence.jpa.repositories;

import com.acme.web.services.user.domain.model.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
