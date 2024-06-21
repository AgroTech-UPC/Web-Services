package com.acme.web.services.user.application.internal.eventhandlers;

import com.acme.web.services.appointment.domain.model.events.CreateNotificationByAppointmentCreated;
import com.acme.web.services.user.domain.model.commands.CreateNotificationCommand;
import com.acme.web.services.user.domain.services.NotificationCommandService;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.AdvisorRepository;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.BreederRepository;
import com.acme.web.services.user.interfaces.rest.resources.CreateNotificationResource;
import com.acme.web.services.user.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AppointmentCreatedEventHandler {
    private final BreederRepository breederRepository;
    private final AdvisorRepository advisorRepository;
    private final NotificationCommandService notificationCommandService;

    public AppointmentCreatedEventHandler(NotificationCommandService notificationCommandService, BreederRepository breederRepository, AdvisorRepository advisorRepository) {
        this.breederRepository = breederRepository;
        this.advisorRepository = advisorRepository;
        this.notificationCommandService = notificationCommandService;
    }

    @EventListener
    public void onAppointmentCreated(CreateNotificationByAppointmentCreated event) {
        Date date = new Date();

        var breeder = breederRepository.findById(event.getBreederId()).orElseThrow();
        var advisor = advisorRepository.findById(event.getAdvisorId()).orElseThrow();

        var meetingUrl = "https://meet.jit.si/AgroConnectMeeting" + event.getBreederId() + "-" + event.getAdvisorId();


        notificationCommandService.handle(new CreateNotificationCommand("Appointment", "Se ha creado una nueva cita para criador", date, breeder.getUserId(), meetingUrl));
        notificationCommandService.handle(new CreateNotificationCommand("Appointment", "Se ha creado una nueva cita para asesor", date, advisor.getUserId(), meetingUrl));
    }

}