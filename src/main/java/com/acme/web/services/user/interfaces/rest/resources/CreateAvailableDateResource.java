package com.acme.web.services.user.interfaces.rest.resources;

import java.time.LocalTime;
import java.util.Date;

public record CreateAvailableDateResource(Long advisorId, Date date, LocalTime startTime, LocalTime endTime) {
}
