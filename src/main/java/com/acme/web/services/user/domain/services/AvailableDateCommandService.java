package com.acme.web.services.user.domain.services;

import com.acme.web.services.user.domain.model.commands.CreateAvailableDateCommand;
import com.acme.web.services.user.domain.model.commands.DeleteAvailableDateCommand;

public interface AvailableDateCommandService {
    Long handle(CreateAvailableDateCommand command);
    void handle(DeleteAvailableDateCommand command);
}
