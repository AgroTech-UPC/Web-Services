package com.acme.web.services.management.domain.model.aggregates;

import com.acme.web.services.management.domain.model.commands.CreateCageCommand;
import com.acme.web.services.management.domain.model.valueobjects.Name;
import com.acme.web.services.management.domain.model.valueobjects.Observations;
import com.acme.web.services.management.domain.model.valueobjects.Size;
import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.web.services.user.domain.model.entities.Breeder;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class Cage extends AuditableAbstractAggregateRoot<Cage> {
    @Embedded
    private Name name;
    @Embedded
    private Size size;
    @Embedded
    private Observations observations;

    @ManyToOne
    @JoinColumn(name = "breeder_id")
    private Breeder breeder;

    public Cage(){}

    public Cage(Name name, Size size, Observations observations, Breeder breeder) {
        this.name = name;
        this.size = size;
        this.observations = observations;
        this.breeder = breeder;
    }

    public Cage(CreateCageCommand command, Breeder breeder) {
        this.name = new Name(command.name());
        this.size = new Size(command.size());
        this.observations = new Observations(command.observations());
        this.breeder = breeder;
    }

    //getters
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
