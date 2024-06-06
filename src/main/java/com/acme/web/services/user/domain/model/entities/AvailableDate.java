package com.acme.web.services.user.domain.model.entities;

import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;


@Getter
@Entity
public class AvailableDate extends AuditableAbstractAggregateRoot<AvailableDate> {
}
