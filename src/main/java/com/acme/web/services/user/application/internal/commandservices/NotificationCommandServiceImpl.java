package com.acme.web.services.user.application.internal.commandservices;

import com.acme.web.services.user.domain.model.commands.CreateNotificationCommand;
import com.acme.web.services.user.domain.model.commands.DeleteNotificationCommand;
import com.acme.web.services.user.domain.model.entities.Notification;
import com.acme.web.services.user.domain.services.NotificationCommandService;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.NotificationRepository;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateNotificationCommand command) {
        var user = userRepository.findById(command.userId());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User does not exist");
        }
        var notification = new Notification(command.type(), command.text(), command.date(), user.get());
        try {
            notificationRepository.save(notification);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving notification: " + e.getMessage());
        }
        return notification.getId();
    }

    @Override
    public void handle(DeleteNotificationCommand command) {
        if (!notificationRepository.existsById(command.notificationId())) {
            throw new IllegalArgumentException("Notification does not exist");
        }
        try {
            notificationRepository.deleteById(command.notificationId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting notification: " + e.getMessage());
        }
    }

}