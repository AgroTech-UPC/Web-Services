package com.acme.web.services.management.interfaces.rest.resources;

public record UpdateCageResource(String name,
                                Integer size,
                                String observations,
                                Long breederId) {
}
