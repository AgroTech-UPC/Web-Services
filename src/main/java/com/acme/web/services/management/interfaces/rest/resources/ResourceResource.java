package com.acme.web.services.management.interfaces.rest.resources;

public record ResourceResource(Long id,
                               String name,
                               String type,
                               Integer quantity,
                               java.time.LocalDate date,
                               String observations,
                               Long breederId) {
}
