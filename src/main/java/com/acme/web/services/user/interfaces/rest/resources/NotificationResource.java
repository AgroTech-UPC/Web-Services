package com.acme.web.services.user.interfaces.rest.resources;

import com.acme.web.services.user.domain.model.aggregates.User;

import java.time.Instant;

public record NotificationResource(Long id, String type, String text, Instant date, Long userId) {
}
