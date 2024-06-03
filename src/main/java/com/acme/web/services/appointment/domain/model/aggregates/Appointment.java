package com.acme.web.services.appointment.domain.model.aggregates;

import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.web.services.appointment.domain.model.commands.CreateAppointmentCommand;
import com.acme.web.services.appointment.domain.model.valueObjects.*;

import com.acme.web.services.user.domain.model.entities.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;


@Getter
@Entity
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {
    @Embedded
    @Column(name="date")
    private final DateAppointment date;

    @Embedded
    @Column(name="status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "breeder_id")
    private Breeder breeder;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    private Advisor advisor;

    public Appointment(){
        this.date = new DateAppointment();
        this.status = new Status();
    }

    public Appointment(CreateAppointmentCommand command){
        this.date = new DateAppointment(command.date());
        this.status = new Status(command.status());
    }

    public Appointment(Breeder breeder, Advisor advisor, CreateAppointmentCommand command){
        this.date = new DateAppointment(command.date());
        this.status = new Status(command.status());
        this.breeder = breeder;
        this.advisor = advisor;
    }

    public void setStatus(String newStatus) {
        this.status = new Status(newStatus);
    }

    public Date getAppointmentDate(){
        return this.date.date();
    }

    public String getStatus() { return this.status.status();}




}
