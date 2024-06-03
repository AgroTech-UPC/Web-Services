package com.acme.web.services.user.interfaces.rest.resources;

import com.acme.web.services.user.domain.model.aggregates.User;

import java.time.Instant;

public record CreateNotificationResource(String type, String text, Instant date, Long userId) {
}
