package com.acme.web.services.user.interfaces.rest;

import com.acme.web.services.user.domain.model.queries.GetAllBreedersQuery;
import com.acme.web.services.user.domain.model.queries.GetBreederByIdQuery;
import com.acme.web.services.user.domain.services.BreederCommandService;
import com.acme.web.services.user.domain.services.BreederQueryService;
import com.acme.web.services.user.interfaces.rest.resources.BreederResource;
import com.acme.web.services.user.interfaces.rest.resources.CreateBreederResource;
import com.acme.web.services.user.interfaces.rest.transform.BreederResourceFromEntityAssembler;
import com.acme.web.services.user.interfaces.rest.transform.CreateBreederCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="/api/v1/breeders", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Breeders", description = "Breeder Management Endpoints")
public class BreedersController {
    private final BreederCommandService breederCommandService;
    private final BreederQueryService breederQueryService;

    public BreedersController(BreederCommandService breederCommandService, BreederQueryService breederQueryService) {
        this.breederCommandService = breederCommandService;
        this.breederQueryService = breederQueryService;
    }

    @PostMapping
    public ResponseEntity<BreederResource> createBreeder(@RequestBody CreateBreederResource resource) {
        var createBreederCommand = CreateBreederCommandFromResourceAssembler.toCommandFromResource(resource);
        var breederId = breederCommandService.handle(createBreederCommand);
        if (breederId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getBreederByIdQuery = new GetBreederByIdQuery(breederId);
        var breeder = breederQueryService.handle(getBreederByIdQuery);
        if (breeder.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var breederResource = BreederResourceFromEntityAssembler.toResourceFromEntity(breeder.get());
        return new ResponseEntity<>(breederResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BreederResource>> getAllBreeders() {
        var getAllBreedersQuery = new GetAllBreedersQuery();
        var breeders = breederQueryService.handle(getAllBreedersQuery);
        var breederResources = breeders.stream().map(BreederResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(breederResources);
    }

    @GetMapping("/{breederId}")
    public ResponseEntity<BreederResource> getBreederById(@PathVariable Long breederId) {
        var getBreederByIdQuery = new GetBreederByIdQuery(breederId);
        var breeder = breederQueryService.handle(getBreederByIdQuery);
        if (breeder.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var breederResource = BreederResourceFromEntityAssembler.toResourceFromEntity(breeder.get());
        return ResponseEntity.ok(breederResource);
    }
}
