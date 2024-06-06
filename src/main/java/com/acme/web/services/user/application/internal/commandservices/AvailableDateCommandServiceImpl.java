package com.acme.web.services.user.application.internal.commandservices;

import com.acme.web.services.user.domain.model.commands.CreateAvailableDateCommand;
import com.acme.web.services.user.domain.model.commands.DeleteAvailableDateCommand;
import com.acme.web.services.user.domain.model.entities.AvailableDate;
import com.acme.web.services.user.domain.services.AvailableDateCommandService;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.AdvisorRepository;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.AvailableDateRepository;
import org.springframework.stereotype.Service;

@Service
public class AvailableDateCommandServiceImpl implements AvailableDateCommandService {
    private final AvailableDateRepository availableDateRepository;
    private final AdvisorRepository advisorRepository;

    public AvailableDateCommandServiceImpl(AvailableDateRepository availableDateRepository, AdvisorRepository advisorRepository) {
        this.availableDateRepository = availableDateRepository;
        this.advisorRepository = advisorRepository;
    }

    @Override
    public Long handle(CreateAvailableDateCommand command) {
        var advisor = advisorRepository.findById(command.advisorId());
        if (advisor.isEmpty()) {
            throw new IllegalArgumentException("Advisor does not exist");
        }
        var availableDate = new AvailableDate(advisor.get(), command.date(), command.startTime(), command.endTime(), true);
        try {
            availableDateRepository.save(availableDate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving available date: " + e.getMessage());
        }
        return availableDate.getId();
    }

    @Override
    public void handle(DeleteAvailableDateCommand command) {
        if (!availableDateRepository.existsById(command.availableDateId())) {
            throw new IllegalArgumentException("Available date does not exist");
        }
        try {
            availableDateRepository.deleteById(command.availableDateId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting available date: " + e.getMessage());
        }
    }
}
