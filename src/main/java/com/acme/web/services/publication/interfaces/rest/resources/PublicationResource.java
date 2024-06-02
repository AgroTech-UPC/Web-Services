package com.acme.web.services.publication.interfaces.rest.resources;

import com.acme.web.services.publication.domain.model.valueobjects.PublicationContent;

import java.util.Date;

public record PublicationResource(
        Long id,
        String title,
        String description,
        String image,
        Date date) { }
