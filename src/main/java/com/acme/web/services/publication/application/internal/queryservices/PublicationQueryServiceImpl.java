package com.acme.web.services.publication.application.internal.queryservices;

import com.acme.web.services.publication.domain.model.aggregates.Publication;
import com.acme.web.services.publication.domain.model.queries.GetAllPublicationsQuery;
import com.acme.web.services.publication.domain.model.queries.GetPublicationByIdQuery;
import com.acme.web.services.publication.domain.services.PublicationQueryService;
import com.acme.web.services.publication.infrastructure.persistence.jpa.repositories.PublicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationQueryServiceImpl implements PublicationQueryService {
    private final PublicationRepository publicationRepository;

    public PublicationQueryServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public List<Publication> handle(GetAllPublicationsQuery query) {
        return publicationRepository.findAll();
    }

    @Override
    public Optional<Publication> handle(GetPublicationByIdQuery query) {
        return publicationRepository.findById(query.publicationId());
    }

}
