package com.acme.web.services.user.domain.model.entities;

import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Entity
public class AvailableDate extends AuditableAbstractAggregateRoot<AvailableDate> {

    @Column(name="date")
    private final LocalDate date;

    @Column(name="start_time")
    private final LocalTime startTime;

    @Column(name="end_time")
    private final LocalTime endTime;

    @Setter
    @Column(name="status", updatable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

    public AvailableDate(){
        this.date = null;
        this.startTime = null;
        this.endTime = null;
        this.status = true;
    }
    public AvailableDate(Advisor advisor, LocalDate date, LocalTime startTime, LocalTime endTime, boolean status){
        this.advisor = advisor;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public LocalDate getAvailableDate(){
        return this.date;
    }
    public Boolean getStatus(){
        return this.status;
    }
}