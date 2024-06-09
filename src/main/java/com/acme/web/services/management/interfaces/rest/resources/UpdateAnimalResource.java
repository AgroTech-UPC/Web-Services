package com.acme.web.services.management.interfaces.rest.resources;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateAnimalResource(
        String name,
        String breed,
        Boolean gender,
        LocalDate birthdate,
        Float weight,
        Boolean isSick,
        String observations,
        Long cageId) {
}
