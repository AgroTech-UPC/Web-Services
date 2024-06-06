package com.acme.web.services.user.interfaces.rest.resources;

import java.time.LocalTime;
import java.util.Date;

public record AvailableDateResource(Long id, Long advisorId, Date date, LocalTime startTime, LocalTime endTime) {
}
