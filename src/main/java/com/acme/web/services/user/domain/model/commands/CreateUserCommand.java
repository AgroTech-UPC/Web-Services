package com.acme.web.services.user.domain.model.commands;

import java.util.Date;

public record CreateUserCommand(String email, String password, String fullname, String location, Date birthdate, String description){

}
