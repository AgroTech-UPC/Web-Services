package com.acme.web.services.user.domain.model.commands;

import java.time.LocalDate;
import java.util.Date;

public record CreateUserCommand(String email, String password, String fullname, String location, LocalDate birthdate, String description){

}
