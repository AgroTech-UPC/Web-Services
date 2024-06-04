package com.acme.web.services.management.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

public record CreateCageResource(
        @NotNull String name,
        @NotNull Integer size,
        @NotNull String observations,
        @NotNull Long breederId) {
}
