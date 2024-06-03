package com.acme.web.services.user.application.internal.queryservices;

import com.acme.web.services.user.domain.model.entities.Breeder;
import com.acme.web.services.user.domain.model.queries.GetAllBreedersQuery;
import com.acme.web.services.user.domain.model.queries.GetBreederByIdQuery;
import com.acme.web.services.user.domain.services.BreederQueryService;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.BreederRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BreederQueryServiceImpl implements BreederQueryService {
    private final BreederRepository breederRepository;

    public BreederQueryServiceImpl(BreederRepository breederRepository){
        this.breederRepository = breederRepository;
    }

    @Override
    public List<Breeder> handle(GetAllBreedersQuery query){
        return breederRepository.findAll();
    }

    @Override
    public Optional<Breeder> handle(GetBreederByIdQuery query){
        return breederRepository.findById(query.breederId());
    }
}
