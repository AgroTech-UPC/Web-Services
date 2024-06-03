package com.acme.web.services.user.interfaces.rest.resources;

import java.util.Date;

public record CreateUserResource(String email, String password, String fullname, String location, Date birthdate, String description) {
}
