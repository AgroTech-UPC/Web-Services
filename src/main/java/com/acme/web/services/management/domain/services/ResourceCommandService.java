package com.acme.web.services.management.domain.services;

import com.acme.web.services.management.domain.model.aggregates.Resource;
import com.acme.web.services.management.domain.model.commands.CreateResourceCommand;
import com.acme.web.services.management.domain.model.commands.UpdateResourceCommand;

import java.util.Optional;

public interface ResourceCommandService {
    Long handle(CreateResourceCommand command);
    Optional<Resource> handle(UpdateResourceCommand command);
}
