package com.acme.web.services.user.interfaces.rest;

import com.acme.web.services.appointment.domain.model.events.CreateNotificationByAppointmentCreated;
import com.acme.web.services.user.domain.model.commands.DeleteNotificationCommand;
import com.acme.web.services.user.domain.model.queries.GetAllNotificationsQuery;
import com.acme.web.services.user.domain.model.queries.GetNotificationByIdQuery;
import com.acme.web.services.user.domain.services.NotificationCommandService;
import com.acme.web.services.user.domain.services.NotificationQueryService;
import com.acme.web.services.user.interfaces.rest.resources.CreateNotificationResource;
import com.acme.web.services.user.interfaces.rest.resources.NotificationResource;
import com.acme.web.services.user.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import com.acme.web.services.user.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/api/v1/notifications", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Notifications", description = "Notification Management Endpoints")
public class NotificationsController {
    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public NotificationsController(NotificationCommandService notificationCommandService, NotificationQueryService notificationQueryService) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
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

    @PostMapping
    public ResponseEntity<NotificationResource> createNotification(@RequestBody CreateNotificationResource resource) {
        var createNotificationCommand = CreateNotificationCommandFromResourceAssembler.toCommandFromResource(resource);
        var notificationId = notificationCommandService.handle(createNotificationCommand);
        if (notificationId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getNotificationByIdQuery = new GetNotificationByIdQuery(notificationId);
        var notification = notificationQueryService.handle(getNotificationByIdQuery);
        if (notification.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
        return new ResponseEntity<>(notificationResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<NotificationResource>> getAllNotifications() {
        var getAllNotificationsQuery = new GetAllNotificationsQuery();
        var notifications = notificationQueryService.handle(getAllNotificationsQuery);
        var notificationResources = notifications.stream().map(NotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(notificationResources);
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResource> getNotificationById(@PathVariable Long notificationId) {
        var getNotificationByIdQuery = new GetNotificationByIdQuery(notificationId);
        var notification = notificationQueryService.handle(getNotificationByIdQuery);
        if (notification.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
        return ResponseEntity.ok(notificationResource);
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long notificationId) {
        var deleteNotificationCommand = new DeleteNotificationCommand(notificationId);
        notificationCommandService.handle(deleteNotificationCommand);
        return ResponseEntity.ok("Notification with given id successfully deleted");
    }
}
