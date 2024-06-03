package com.acme.web.services.management.domain.model.aggregates;

import com.acme.web.services.management.domain.model.commands.CreateResourceCommand;
import com.acme.web.services.management.domain.model.valueobjects.*;
import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.web.services.user.domain.model.entities.Breeder;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

/**
 * Resource entity
 */
@Getter
@Entity
public class Resource extends AuditableAbstractAggregateRoot<Resource> {
    @Embedded
    private Name name;
    @Enumerated(EnumType.STRING)
    @Embedded
    private ResourceType type;
    @Embedded
    private Quantity quantity;
    @Embedded
    private DateOfCreation date;
    @Embedded
    private Observations observations;


    @ManyToOne
    @JoinColumn(name = "breeder_id")
    private Breeder breeder;

    public Resource(){}

    /**
     * Constructor
     * @param name
     * @param type
     * @param quantity
     * @param date
     * @param observations
     * @param breeder
     */
    public Resource(Name name, ResourceType type, Quantity quantity, DateOfCreation date, Observations observations, Breeder breeder) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.date = date;
        this.observations = observations;
        this.breeder = breeder;
    }

    /**
     * Constructor
     * @param command
     * @param breeder
     */
    public Resource(CreateResourceCommand command, Breeder breeder) {
        this.name = new Name(command.name());
        this.type = ResourceType.valueOf(command.type().toUpperCase());
        this.quantity = new Quantity(command.quantity());
        this.date = new DateOfCreation(command.date());
        this.observations = new Observations(command.observations());
        this.breeder = breeder;
    }

    //Update methods
    public void updateName(String  name) {
        this.name = new Name(name);
    }

    public void updateType(String type) {
        this.type = ResourceType.valueOf(type.toUpperCase());
    }

    public void updateQuantity(Integer quantity) {
        this.quantity = new Quantity(quantity);
    }

    public void updateDate(Date date) {
        this.date = new DateOfCreation(date);
    }

    public void updateObservations(String observations) {
        this.observations = new Observations(observations);
    }

    //Getters
    public Name name() {
        return name;
    }

    public ResourceType type() {
        return type;
    }

    public Quantity quantity() {
        return quantity;
    }

    public DateOfCreation date() {
        return date;
    }

    public Observations observations() {
        return observations;
    }
}
