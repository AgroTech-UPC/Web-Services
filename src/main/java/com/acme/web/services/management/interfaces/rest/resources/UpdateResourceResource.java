package com.acme.web.services.management.interfaces.rest.resources;

import java.time.LocalDate;

public record UpdateResourceResource(String name,
                                     String type,
                                     Integer quantity,
                                     LocalDate date,
                                     String observations,
                                     Long breederId) {
}
