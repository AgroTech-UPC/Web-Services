package com.acme.web.services.appointment.infrastructure.persistance.jpa.repositories;

import com.acme.web.services.appointment.domain.model.aggregates.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByBreederId(Long breederId);
    List<Appointment> findAllByAdvisorId(Long advisorId);
}
