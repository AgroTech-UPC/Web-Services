package com.acme.web.services.user.interfaces.rest.resources;

import com.acme.web.services.user.domain.model.aggregates.User;

public record CreateAdvisorResource(String occupation, int experience, String photo, int rating, Long userId) {
}
