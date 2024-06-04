package com.acme.web.services.user.application.internal.queryservices;

import com.acme.web.services.user.domain.model.entities.Advisor;
import com.acme.web.services.user.domain.model.queries.GetAdvisorByIdQuery;
import com.acme.web.services.user.domain.model.queries.GetAllAdvisorsQuery;
import com.acme.web.services.user.domain.services.AdvisorQueryService;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.AdvisorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvisorQueryServiceImpl implements AdvisorQueryService {
    private final AdvisorRepository advisorRepository;

    public AdvisorQueryServiceImpl(AdvisorRepository advisorRepository){
        this.advisorRepository = advisorRepository;
    }

    @Override
    public List<Advisor> handle(GetAllAdvisorsQuery query){
        return advisorRepository.findAll();
    }

    @Override
    public Optional<Advisor> handle(GetAdvisorByIdQuery query){
        return advisorRepository.findById(query.advisorId());
    }

}