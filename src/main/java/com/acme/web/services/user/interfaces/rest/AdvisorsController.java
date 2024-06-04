package com.acme.web.services.user.interfaces.rest;

import com.acme.web.services.user.domain.model.queries.GetAdvisorByIdQuery;
import com.acme.web.services.user.domain.model.queries.GetAllAdvisorsQuery;
import com.acme.web.services.user.domain.services.AdvisorCommandService;
import com.acme.web.services.user.domain.services.AdvisorQueryService;
import com.acme.web.services.user.interfaces.rest.resources.AdvisorResource;
import com.acme.web.services.user.interfaces.rest.resources.CreateAdvisorResource;
import com.acme.web.services.user.interfaces.rest.transform.AdvisorResourceFromEntityAssembler;
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

    public AdvisorsController(AdvisorCommandService advisorCommandService, AdvisorQueryService advisorQueryService) {
        this.advisorCommandService = advisorCommandService;
        this.advisorQueryService = advisorQueryService;
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


}
