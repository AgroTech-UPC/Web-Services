package com.acme.web.services.management.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateResourceResource(@NotNull String name,
                                     @NotNull String type,
                                     @NotNull Integer quantity,
                                     @NotNull Date date,
                                     @NotNull String observations,
                                     @NotNull Long breederId) {
}
