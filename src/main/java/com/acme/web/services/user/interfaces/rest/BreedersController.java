package com.acme.web.services.user.interfaces.rest;

import com.acme.web.services.management.domain.model.queries.GetAllCagesByBreederIdQuery;
import com.acme.web.services.management.domain.services.CageQueryService;
import com.acme.web.services.management.interfaces.rest.resources.CageResource;
import com.acme.web.services.management.interfaces.rest.transform.CageResourceFromEntityAssembler;
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
    private final CageQueryService cageQueryService;

    public BreedersController(BreederCommandService breederCommandService, BreederQueryService breederQueryService, CageQueryService cageQueryService){
        this.breederCommandService = breederCommandService;
        this.breederQueryService = breederQueryService;
        this.cageQueryService = cageQueryService;
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

    //GET method to get all cages by breeder id
    @GetMapping("/{breederId}/cages")
    public ResponseEntity<List<CageResource>> getCagesByBreederId(@PathVariable Long breederId) {
        var getAllCagesByBreederIdQuery = new GetAllCagesByBreederIdQuery(breederId);
        var cages = cageQueryService.handle(getAllCagesByBreederIdQuery);
        var cageResources = cages.stream().map(CageResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(cageResources);
    }
}
