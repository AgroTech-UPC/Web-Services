package com.acme.web.services.user.domain.model.commands;

import com.acme.web.services.user.domain.model.aggregates.User;

import java.util.Date;

public record CreateNotificationCommand(String type, String text, Date date, Long userId) {
}
