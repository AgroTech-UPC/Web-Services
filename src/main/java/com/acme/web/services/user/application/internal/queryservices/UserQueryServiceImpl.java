package com.acme.web.services.user.application.internal.queryservices;

import com.acme.web.services.user.domain.model.aggregates.User;
import com.acme.web.services.user.domain.model.queries.GetAllUsersQuery;
import com.acme.web.services.user.domain.model.queries.GetUserByIdQuery;
import com.acme.web.services.user.domain.services.UserQueryService;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService{
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<User> handle(GetAllUsersQuery query){
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query){
        return userRepository.findById(query.userId());
    }


}
