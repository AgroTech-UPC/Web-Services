package com.acme.web.services.management.domain.services;

import com.acme.web.services.management.domain.model.aggregates.Resource;
import com.acme.web.services.management.domain.model.queries.GetAllResourcesByTypeAndIdQuery;
import com.acme.web.services.management.domain.model.queries.GetAllResourcesQuery;
import com.acme.web.services.management.domain.model.queries.GetResourceByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Resource query service interface.
 */
public interface ResourceQueryService {
    List<Resource> handle(GetAllResourcesQuery query);
    Optional<Resource> handle(GetResourceByIdQuery query);
    List<Resource> handle(GetAllResourcesByTypeAndIdQuery query);
}
