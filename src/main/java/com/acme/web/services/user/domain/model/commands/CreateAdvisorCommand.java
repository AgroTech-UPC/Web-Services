package com.acme.web.services.user.domain.model.commands;

import com.acme.web.services.user.domain.model.aggregates.User;

public record CreateAdvisorCommand(String occupation, int experience, String photo, int rating, Long userId) {
}
