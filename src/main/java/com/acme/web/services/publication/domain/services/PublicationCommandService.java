package com.acme.web.services.publication.domain.services;

import com.acme.web.services.publication.domain.model.commands.CreatePublicationCommand;


public interface PublicationCommandService {
    Long handle(CreatePublicationCommand command);
}