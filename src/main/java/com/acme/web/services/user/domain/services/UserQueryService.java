package com.acme.web.services.user.domain.services;

import com.acme.web.services.user.domain.model.aggregates.User;
import com.acme.web.services.user.domain.model.queries.GetAllUsersQuery;
import com.acme.web.services.user.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
}
