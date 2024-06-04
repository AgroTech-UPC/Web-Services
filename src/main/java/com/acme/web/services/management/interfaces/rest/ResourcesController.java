package com.acme.web.services.management.interfaces.rest;

import com.acme.web.services.management.domain.model.commands.DeleteResourceCommand;
import com.acme.web.services.management.domain.model.queries.GetAllResourcesQuery;
import com.acme.web.services.management.domain.model.queries.GetResourceByIdQuery;
import com.acme.web.services.management.domain.services.ResourceCommandService;
import com.acme.web.services.management.domain.services.ResourceQueryService;
import com.acme.web.services.management.interfaces.rest.resources.CreateResourceResource;
import com.acme.web.services.management.interfaces.rest.transform.ResourceResourceFromEntityAssembler;
import com.acme.web.services.management.interfaces.rest.resources.ResourceResource;
import com.acme.web.services.management.interfaces.rest.transform.CreateResourceCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/resources", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Resources", description = "Resource Management Endpoints")
public class ResourcesController {
    private final ResourceCommandService resourceCommandService;
    private final ResourceQueryService resourceQueryService;

    public ResourcesController(ResourceCommandService resourceCommandService, ResourceQueryService resourceQueryService) {
        this.resourceCommandService = resourceCommandService;
        this.resourceQueryService = resourceQueryService;
    }

    @PostMapping
    public ResponseEntity<ResourceResource> createResource(@RequestBody CreateResourceResource res) {
        var createResourceCommand = CreateResourceCommandFromResourceAssembler.toCommandFromResource(res);
        var resourceId = resourceCommandService.handle(createResourceCommand);
        if (resourceId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getResourceByIdQuery = new GetResourceByIdQuery(resourceId);
        var resource = resourceQueryService.handle(getResourceByIdQuery);
        if (resource.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var resourceResource = ResourceResourceFromEntityAssembler.toResourceFromEntity(resource.get());
        return new ResponseEntity<>(resourceResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ResourceResource>> getAllResources() {
        var getAllResourcesQuery = new GetAllResourcesQuery();
        var resources = resourceQueryService.handle(getAllResourcesQuery);
        var resourceResources = resources.stream().map(ResourceResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resourceResources);
    }

    @GetMapping("/{resourceId}")
    public ResponseEntity<ResourceResource> getResourceById(@PathVariable Long resourceId) {
        var getResourceByIdQuery = new GetResourceByIdQuery(resourceId);
        var resource = resourceQueryService.handle(getResourceByIdQuery);
        if (resource.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var resourceResource = ResourceResourceFromEntityAssembler.toResourceFromEntity(resource.get());
        return ResponseEntity.ok(resourceResource);
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<ResourceResource> updateResource(@PathVariable Long resourceId, @RequestBody CreateResourceResource res) {
        var createResourceCommand = CreateResourceCommandFromResourceAssembler.toCommandFromResource(res);
        var updatedResourceId = resourceCommandService.handle(createResourceCommand);
        if (updatedResourceId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getResourceByIdQuery = new GetResourceByIdQuery(updatedResourceId);
        var resource = resourceQueryService.handle(getResourceByIdQuery);
        if (resource.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var resourceResource = ResourceResourceFromEntityAssembler.toResourceFromEntity(resource.get());
        return new ResponseEntity<>(resourceResource, HttpStatus.CREATED);
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long resourceId) {
        var deleteResourceCommand = new DeleteResourceCommand(resourceId);
        var resourceDeleted = resourceCommandService.handle(deleteResourceCommand);
        if (resourceDeleted.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }
}
