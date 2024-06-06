package com.acme.web.services.user.domain.model.entities;

import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.web.services.user.domain.model.valueobjects.DateAv;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Entity
public class AvailableDate extends AuditableAbstractAggregateRoot<AvailableDate> {

    @Embedded
    @Column(name="date")
    private final DateAv date;

    @Column(name="start_time")
    private final LocalTime startTime;

    @Column(name="end_time")
    private final LocalTime endTime;

    @Setter
    @Column(name="status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

    public AvailableDate(){
        this.date = new DateAv();
        this.startTime = null;
        this.endTime = null;
        this.status = true;
    }
    public AvailableDate(Advisor advisor, DateAv date, LocalTime startTime, LocalTime endTime){
        this.advisor = advisor;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = true;
    }

    public DateAv getAvailableDate(){
        return this.date;
    }
    public Boolean getStatus(){
        return this.status;
    }
}