package com.acme.web.services.user.domain.services;

import com.acme.web.services.user.domain.model.commands.CreateUserCommand;

public interface UserCommandService {
    Long handle(CreateUserCommand command);
}
