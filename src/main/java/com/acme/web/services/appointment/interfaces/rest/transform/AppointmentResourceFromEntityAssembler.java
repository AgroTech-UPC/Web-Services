package com.acme.web.services.appointment.interfaces.rest.transform;

import com.acme.web.services.appointment.domain.model.aggregates.Appointment;
import com.acme.web.services.appointment.interfaces.rest.resources.AppointmentResource;
import com.acme.web.services.user.domain.model.entities.*;

import java.util.stream.Collectors;

public class AppointmentResourceFromEntityAssembler {
    public static AppointmentResource toResourceFromEntity(Appointment entity){
        return new AppointmentResource(
                entity.getId(),
                entity.getAdvisor().getId(),
                entity.getBreeder().getId(),
                entity.getAppointmentDate(),
                entity.getStatus());
    }
}