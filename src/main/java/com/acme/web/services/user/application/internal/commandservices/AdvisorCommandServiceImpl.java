package com.acme.web.services.user.application.internal.commandservices;

import com.acme.web.services.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.acme.web.services.user.domain.model.commands.CreateAdvisorCommand;
import com.acme.web.services.user.domain.model.aggregates.Advisor;
import com.acme.web.services.user.domain.services.AdvisorCommandService;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.AdvisorRepository;
import org.springframework.stereotype.Service;

/**
 * This class represents the service implementation for the AdvisorCommandService.
 * It handles the creation of an advisor.
 * @author Piero Gonzalo Delgado Corrales - U202210749
 * @version 1.0
 */
@Service
public class AdvisorCommandServiceImpl implements AdvisorCommandService{
    private final AdvisorRepository advisorRepository;
    private final UserRepository userRepository;

    public AdvisorCommandServiceImpl(AdvisorRepository advisorRepository, UserRepository userRepository){
        this.advisorRepository = advisorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long handle(CreateAdvisorCommand command){
        var user = userRepository.findById(command.userId());
        if(user.isEmpty()){
            throw new IllegalArgumentException("User does not exist");
        }
        var advisor = new Advisor(command, user.get());
        try{
            advisorRepository.save(advisor);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while saving advisor: " + e.getMessage());
        }
        return advisor.getId();
    }

}