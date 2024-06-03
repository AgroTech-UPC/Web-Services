package com.acme.web.services.user.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateUserResource(String email, String password, String fullname, String location, LocalDate birthdate, String description) {
}
