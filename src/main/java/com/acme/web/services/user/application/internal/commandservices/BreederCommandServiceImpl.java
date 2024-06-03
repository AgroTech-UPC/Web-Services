package com.acme.web.services.user.application.internal.commandservices;

import com.acme.web.services.user.domain.model.aggregates.User;
import com.acme.web.services.user.domain.model.commands.CreateBreederCommand;
import com.acme.web.services.user.domain.model.entities.Breeder;
import com.acme.web.services.user.domain.services.BreederCommandService;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.BreederRepository;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BreederCommandServiceImpl implements BreederCommandService {
    private final BreederRepository breederRepository;
    private final UserRepository userRepository;

    public BreederCommandServiceImpl(BreederRepository breederRepository, UserRepository userRepository) {
        this.breederRepository = breederRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateBreederCommand command) {
        var user = userRepository.findById(command.userId());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User does not exist");
        }
        var breeder = new Breeder(user.get());
        try {
            breederRepository.save(breeder);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving breeder: " + e.getMessage());
        }
        return breeder.getId();
    }

}