package com.acme.web.services.appointment.application.internal.queryservices;

import com.acme.web.services.appointment.domain.model.aggregates.Appointment;
import com.acme.web.services.appointment.domain.model.queries.GetAllAppointmentsQuery;
import com.acme.web.services.appointment.domain.model.queries.GetAppointmentByIdQuery;
import com.acme.web.services.appointment.domain.services.AppointmentQueryService;
import com.acme.web.services.appointment.infrastructure.persistance.jpa.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository){
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsQuery query){
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> handle(GetAppointmentByIdQuery query){
        return appointmentRepository.findById(query.appointmentId());
    }
}