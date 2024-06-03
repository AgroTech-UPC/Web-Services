package com.acme.web.services.user.domain.model.commands;

import com.acme.web.services.user.domain.model.aggregates.User;

import java.time.Instant;

public record CreateNotificationCommand(String type, String text, Instant date, Long userId) {
}
