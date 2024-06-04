package com.acme.web.services.management.interfaces.rest.resources;

import java.util.Date;

public record UpdateResourceResource(String name,
                                     String type,
                                     Integer quantity,
                                     Date date,
                                     String observations,
                                     Long breederId) {
}
