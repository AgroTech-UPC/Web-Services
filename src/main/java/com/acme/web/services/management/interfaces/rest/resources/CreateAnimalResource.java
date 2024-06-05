package com.acme.web.services.management.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateAnimalResource (
    @NotNull String name,
    @NotNull String breed,
    @NotNull Boolean gender,
    @NotNull LocalDate birthdate,
    @NotNull Float weight,
    @NotNull Boolean isSick,
    @NotNull String observations,
    @NotNull Long cageId) {
}
