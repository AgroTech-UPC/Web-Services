package com.acme.web.services.user.domain.model.commands;

import java.time.LocalTime;
import java.util.Date;

public record CreateAvailableDateCommand(Long advisorId, Date date, LocalTime startTime, LocalTime endTime) {
}
