package com.acme.web.services.user.application.internal.commandservices;

import com.acme.web.services.user.domain.model.aggregates.User;
import com.acme.web.services.user.domain.model.commands.CreateUserCommand;
import com.acme.web.services.user.domain.services.UserCommandService;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    public UserCommandServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public Long handle(CreateUserCommand command) {
        if(userRepository.existsByEmailAddress_Email(command.email())){
            throw new IllegalArgumentException("Email already being used");
        }
        var user = new User(command);
        try{
            userRepository.save(user);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while saving user: " + e.getMessage());
        }
        return user.getId();
    }

}
