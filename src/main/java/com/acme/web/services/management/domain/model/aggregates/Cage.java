package com.acme.web.services.management.domain.model.aggregates;

import com.acme.web.services.management.domain.model.commands.CreateCageCommand;
import com.acme.web.services.management.domain.model.valueobjects.Name;
import com.acme.web.services.management.domain.model.valueobjects.Observations;
import com.acme.web.services.management.domain.model.valueobjects.Size;
import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.web.services.user.domain.model.aggregates.Breeder;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Cage aggregate root
 */
@Getter
@Entity
public class Cage extends AuditableAbstractAggregateRoot<Cage> {
    @Setter
    @Embedded
    private Name name;

    @Setter
    @Embedded
    private Size size;

    @Setter
    @Embedded
    private Observations observations;

    @ManyToOne
    @JoinColumn(name = "breeder_id")
    private Breeder breeder;

    /**
     * Constructor
     * @param name
     * @param size
     * @param observations
     * @param breeder
     */
    public Cage(String name, Integer size, String observations, Breeder breeder){
        this.name = new Name(name);
        this.size = new Size(size);
        this.observations = new Observations(observations);
        this.breeder = breeder;
    }

    /**
     * Constructor
     * @param name
     * @param size
     * @param observations
     * @param breeder
     */
    public Cage(Name name, Size size, Observations observations, Breeder breeder) {
        this.name = name;
        this.size = size;
        this.observations = observations;
        this.breeder = breeder;
    }

    /**
     * Constructor
     * @param command
     * @param breeder
     */
    public Cage(CreateCageCommand command, Breeder breeder) {
        this.name = new Name(command.name());
        this.size = new Size(command.size());
        this.observations = new Observations(command.observations());
        this.breeder = breeder;
    }

    public Cage() {}

    public String name() {
        return this.name.name();
    }

    public Integer size() {
        return this.size.size();
    }

    public String observations() {
        return this.observations.observations();
    }
}