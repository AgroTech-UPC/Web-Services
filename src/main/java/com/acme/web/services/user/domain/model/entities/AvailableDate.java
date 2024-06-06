package com.acme.web.services.user.domain.model.entities;

import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.web.services.user.domain.model.valueobjects.DateAv;
import com.acme.web.services.user.domain.model.valueobjects.TimeAv;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Entity
public class AvailableDate extends AuditableAbstractAggregateRoot<AvailableDate> {

    @Embedded
    @Column(name="date")
    private final DateAv date;

    @Embedded
    @Column(name="start_time")
    private final TimeAv startTime;

    @Embedded
    @Column(name="end_time")
    private final TimeAv endTime;


    @Setter
    @Column(name="status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

    public AvailableDate(){
        this.date = new DateAv();
        this.startTime = new TimeAv();
        this.endTime = new TimeAv();
        this.status = true;
    }
    public AvailableDate(DateAv date, TimeAv startTime, TimeAv endTime){
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
