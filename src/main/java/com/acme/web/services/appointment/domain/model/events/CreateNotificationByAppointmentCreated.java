package com.acme.web.services.appointment.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class CreateNotificationByAppointmentCreated extends ApplicationEvent {

    private final Long breederUserId;
    private final Long advisorUserId;

    public CreateNotificationByAppointmentCreated(Object source, Long breederId, Long advisorId) {
        super(source);
        this.breederUserId = breederId;
        this.advisorUserId = advisorId;
    }

}

