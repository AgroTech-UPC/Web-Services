package com.acme.web.services.management.interfaces.rest.resources;

import java.time.LocalDate;

public record ExpenseResource(Long id,
                              String name,
                              String type,
                              Float amount,
                              LocalDate date,
                              String observations,
                              Long breederId) {
}
