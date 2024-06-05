package com.acme.web.services.appointment.domain.model.entities;

import com.acme.web.services.appointment.domain.model.aggregates.Appointment;
import com.acme.web.services.appointment.domain.model.commands.CreateReviewCommand;
import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;


@Getter
@Entity
public class Review extends AuditableAbstractAggregateRoot<Review>{

    @Getter
    @Column(name="comment", columnDefinition="TEXT")
    private String comment;

    @Getter
    @Column(name="rating")
    private int rating;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    public Review(){
        this.comment = "Sin Comentarios";
        this.rating = 0;
    }

    public Review(CreateReviewCommand command){
        this.comment = command.comment();
        this.rating = command.rating();
    }

    public Review(Appointment appointment, CreateReviewCommand command){
        this.comment = command.comment();
        this.rating = command.rating();
        this.appointment = appointment;
    }

    public String getReviewComment(){
        return this.comment;
    }
    public int getReviewRating(){
        return this.rating;
    }
}
