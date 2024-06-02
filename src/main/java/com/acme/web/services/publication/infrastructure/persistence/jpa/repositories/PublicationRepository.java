package com.acme.web.services.publication.infrastructure.persistence.jpa.repositories;

import com.acme.web.services.publication.domain.model.aggregates.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {

}
