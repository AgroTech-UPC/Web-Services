package com.acme.web.services.management.domain.model.commands;

import java.util.Date;

public record CreateResourceCommand(String name, String type, Integer quantity, Date date, String observations) {
}

