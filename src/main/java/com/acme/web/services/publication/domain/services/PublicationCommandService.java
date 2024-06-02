package com.acme.web.services.publication.domain.services;

import com.acme.web.services.publication.domain.model.aggregates.Publication;
import com.acme.web.services.publication.domain.model.commands.CreatePublicationCommand;

import java.util.Optional;

public interface PublicationCommandService {
    Optional<Publication> handle(CreatePublicationCommand command);
}