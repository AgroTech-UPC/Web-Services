package com.acme.web.services.appointment.domain.model.events;

import org.springframework.context.ApplicationEvent;

public class CreateNotificationByAppointmentCreated extends ApplicationEvent {

    private final Long breederUserId;
    private final Long advisorUserId;

    public CreateNotificationByAppointmentCreated(Object source, Long breederId, Long advisorId) {
        super(source);
        this.breederUserId = breederId;
        this.advisorUserId = advisorId;
    }

    public Long getBreederUserId() {
        return breederUserId;
    }

    public Long getAdvisorUserId() {
        return advisorUserId;
    }
}

