package com.acme.web.services.user.interfaces.rest;

import com.acme.web.services.user.domain.model.queries.GetAllUsersQuery;
import com.acme.web.services.user.domain.model.queries.GetNotificationsByUserIdQuery;
import com.acme.web.services.user.domain.model.queries.GetUserByIdQuery;
import com.acme.web.services.user.domain.services.NotificationQueryService;
import com.acme.web.services.user.domain.services.UserCommandService;
import com.acme.web.services.user.domain.services.UserQueryService;
import com.acme.web.services.user.interfaces.rest.resources.CreateUserResource;
import com.acme.web.services.user.interfaces.rest.resources.NotificationResource;
import com.acme.web.services.user.interfaces.rest.resources.UserResource;
import com.acme.web.services.user.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.acme.web.services.user.interfaces.rest.transform.UserResourceFromEntityAssembler;
import com.acme.web.services.user.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/api/v1/users", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final NotificationQueryService notificationQueryService;

    public UsersController(UserCommandService userCommandService, UserQueryService userQueryService, NotificationQueryService notificationQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
        this.notificationQueryService = notificationQueryService;
    }

    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        var createUserCommand = CreateUserCommandFromResourceAssembler.toCommandFromResource(resource);
        var userId = userCommandService.handle(createUserCommand);
        if (userId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    @GetMapping("/{userId}/notifications")
    public ResponseEntity<List<NotificationResource>> getUserNotifications(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var getNotificationsByUserIdQuery = new GetNotificationsByUserIdQuery(userId);
        var notifications = notificationQueryService.handle(getNotificationsByUserIdQuery);
        var notificationResources = notifications.stream().map(NotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(notificationResources);
    }




}
