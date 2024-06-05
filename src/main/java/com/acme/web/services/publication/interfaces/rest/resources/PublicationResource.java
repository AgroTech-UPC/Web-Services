package com.acme.web.services.publication.interfaces.rest.resources;

import java.util.Date;

public record PublicationResource(
        Long id,
        String title,
        String description,
        String image,
        Date date,
        Long advisorId) { }
