package com.acme.web.services.appointment.infrastructure.persistance.jpa.repositories;

import com.acme.web.services.appointment.domain.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    List<Review> findByAppointmentId(Long appointmentId);
    boolean existsByAppointmentId(Long appointmentId);
}
