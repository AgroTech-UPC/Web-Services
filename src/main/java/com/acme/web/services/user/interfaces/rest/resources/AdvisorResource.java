package com.acme.web.services.user.interfaces.rest.resources;

import com.acme.web.services.user.domain.model.aggregates.User;

public record AdvisorResource(Long id, String occupation, int experience, String photo, int rating, Long userId) {
}
