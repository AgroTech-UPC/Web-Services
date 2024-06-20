package com.acme.web.services.user.application.internal.eventhandlers;

import com.acme.web.services.appointment.domain.model.events.CreateNotificationByAppointmentCreated;
import com.acme.web.services.user.domain.services.NotificationCommandService;
import com.acme.web.services.user.interfaces.rest.resources.CreateNotificationResource;
import com.acme.web.services.user.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserReadyEventHandler {

    private final NotificationCommandService notificationCommandService;

    public UserReadyEventHandler(NotificationCommandService notificationCommandService) {
        this.notificationCommandService = notificationCommandService;
    }

    @EventListener
    public void onAppointmentCreated(CreateNotificationByAppointmentCreated event) {
        Date date = new Date(); // Use current date. Adjust as necessary.
        Long breederUserId = event.getBreederUserId(); // Get the breeder's user ID from the event.
        Long advisorUserId = event.getAdvisorUserId(); // Get the advisor's user ID from the event.

        // Create a notification for the breeder.
        CreateNotificationResource breederNotificationResource = new CreateNotificationResource("Appointment", "Se ha creado una nueva cita para criador", date, breederUserId);
        createNotification(breederNotificationResource);

        // Create a notification for the advisor.
        CreateNotificationResource advisorNotificationResource = new CreateNotificationResource("Appointment", "Se ha creado una nueva cita para asesor", date, advisorUserId);
        createNotification(advisorNotificationResource);
    }

    private void createNotification(CreateNotificationResource resource) {
        var createNotificationCommand = CreateNotificationCommandFromResourceAssembler.toCommandFromResource(resource);
        notificationCommandService.handle(createNotificationCommand);
    }
}