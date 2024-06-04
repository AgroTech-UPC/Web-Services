package com.acme.web.services.user.application.internal.commandservices;

import com.acme.web.services.user.domain.model.commands.CreateAdvisorCommand;
import com.acme.web.services.user.domain.model.entities.Advisor;
import com.acme.web.services.user.domain.services.AdvisorCommandService;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.AdvisorRepository;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

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
        var advisor = new Advisor(command.occupation(), command.experience(), command.photo(), command.rating(), user.get());
        try{
            advisorRepository.save(advisor);
        } catch (Exception e){
            throw new IllegalArgumentException("Error while saving advisor: " + e.getMessage());
        }
        return advisor.getId();
    }

}