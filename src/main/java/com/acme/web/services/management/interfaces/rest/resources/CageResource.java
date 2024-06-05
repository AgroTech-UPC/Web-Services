package com.acme.web.services.management.interfaces.rest.resources;

public record CageResource(Long id, String name, Integer size, String observations, Long breederId) {}