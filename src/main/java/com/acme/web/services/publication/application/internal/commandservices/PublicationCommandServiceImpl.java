package com.acme.web.services.publication.application.internal.commandservices;

import com.acme.web.services.publication.domain.model.aggregates.Publication;
import com.acme.web.services.publication.domain.model.commands.CreatePublicationCommand;
import com.acme.web.services.publication.domain.services.PublicationCommandService;
import com.acme.web.services.publication.infrastructure.persistence.jpa.repositories.PublicationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublicationCommandServiceImpl implements PublicationCommandService {
    private final PublicationRepository publicationRepository;

    public PublicationCommandServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public Optional<Publication> handle(CreatePublicationCommand command) {
        var publication = new Publication(command);
        publicationRepository.save(publication);
        return Optional.of(publication);
    }
}
