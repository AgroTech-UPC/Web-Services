package com.acme.web.services.user.interfaces.rest.resources;

import com.acme.web.services.user.domain.model.aggregates.User;

import java.util.Date;

public record CreateNotificationResource(String type, String text, Date date, Long userId) {
}
