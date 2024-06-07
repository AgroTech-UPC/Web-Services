package com.acme.web.services.user.interfaces.rest;

import com.acme.web.services.appointment.domain.model.queries.GetAllAppointmentsByAdvisorIdQuery;
import com.acme.web.services.appointment.interfaces.rest.resources.AppointmentResource;
import com.acme.web.services.appointment.interfaces.rest.transform.AppointmentResourceFromEntityAssembler;
import com.acme.web.services.user.domain.model.queries.GetAdvisorByIdQuery;
import com.acme.web.services.user.domain.model.queries.GetAllAdvisorsQuery;
import com.acme.web.services.user.domain.model.queries.GetAvailableDatesByAdvisorIdQuery;
import com.acme.web.services.user.domain.services.AdvisorCommandService;
import com.acme.web.services.user.domain.services.AdvisorQueryService;
import com.acme.web.services.user.domain.services.AvailableDateQueryService;
import com.acme.web.services.appointment.domain.services.AppointmentQueryService;
import com.acme.web.services.user.interfaces.rest.resources.AdvisorResource;
import com.acme.web.services.user.interfaces.rest.resources.AvailableDateResource;
import com.acme.web.services.user.interfaces.rest.resources.CreateAdvisorResource;
import com.acme.web.services.user.interfaces.rest.transform.AdvisorResourceFromEntityAssembler;
import com.acme.web.services.user.interfaces.rest.transform.AvailableDateResourceFromEntityAssembler;
import com.acme.web.services.user.interfaces.rest.transform.CreateAdvisorCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/api/v1/advisors", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Advisors", description = "Advisor Management Endpoints")
public class AdvisorsController {
    private final AdvisorCommandService advisorCommandService;
    private final AdvisorQueryService advisorQueryService;
    private final AvailableDateQueryService availableDateQueryService;
    private final AppointmentQueryService appointmentQueryService;

    public AdvisorsController(AdvisorCommandService advisorCommandService, AdvisorQueryService advisorQueryService, AvailableDateQueryService availableDateQueryService, AppointmentQueryService appointmentQueryService) {
        this.advisorCommandService = advisorCommandService;
        this.advisorQueryService = advisorQueryService;
        this.availableDateQueryService = availableDateQueryService;
        this.appointmentQueryService = appointmentQueryService;
    }

    @PostMapping
    public ResponseEntity<AdvisorResource> createAdvisor(@RequestBody CreateAdvisorResource resource) {
        var createAdvisorCommand = CreateAdvisorCommandFromResourceAssembler.toCommandFromResource(resource);
        var advisorId = advisorCommandService.handle(createAdvisorCommand);
        if (advisorId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getAdvisorByIdQuery = new GetAdvisorByIdQuery(advisorId);
        var advisor = advisorQueryService.handle(getAdvisorByIdQuery);
        if (advisor.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var advisorResource = AdvisorResourceFromEntityAssembler.toResourceFromEntity(advisor.get());
        return new ResponseEntity<>(advisorResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AdvisorResource>> getAllAdvisors() {
        var getAllAdvisorsQuery = new GetAllAdvisorsQuery();
        var advisors = advisorQueryService.handle(getAllAdvisorsQuery);
        var advisorResources = advisors.stream().map(AdvisorResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(advisorResources);
    }

    @GetMapping("/{advisorId}")
    public ResponseEntity<AdvisorResource> getAdvisorById(@PathVariable Long advisorId) {
        var getAdvisorByIdQuery = new GetAdvisorByIdQuery(advisorId);
        var advisor = advisorQueryService.handle(getAdvisorByIdQuery);
        if (advisor.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var advisorResource = AdvisorResourceFromEntityAssembler.toResourceFromEntity(advisor.get());
        return ResponseEntity.ok(advisorResource);
    }

    @GetMapping("/{advisorId}/available-dates")
    public ResponseEntity<List<AvailableDateResource>> getAdvisorAvailableDates(@PathVariable Long advisorId) {
        var getAdvisorByIdQuery = new GetAdvisorByIdQuery(advisorId);
        var advisor = advisorQueryService.handle(getAdvisorByIdQuery);
        if (advisor.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var getAvailableDatesByAdvisorIdQuery = new GetAvailableDatesByAdvisorIdQuery(advisorId);
        var availableDates = availableDateQueryService.handle(getAvailableDatesByAdvisorIdQuery);
        var availableDateResources = availableDates.stream().map(AvailableDateResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(availableDateResources);
    }

    //GET method to get all appointments by advisor id
    @GetMapping("/{advisorId}/appointments")
    public ResponseEntity<List<AppointmentResource>> getAppointmentsByAdvisorId(@PathVariable Long advisorId) {
        var getAllAppointmentsByAdvisorIdQuery = new GetAllAppointmentsByAdvisorIdQuery(advisorId);
        var appointments = appointmentQueryService.handle(getAllAppointmentsByAdvisorIdQuery);
        var appointmentResources = appointments.stream().map(AppointmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(appointmentResources);
    }

}
