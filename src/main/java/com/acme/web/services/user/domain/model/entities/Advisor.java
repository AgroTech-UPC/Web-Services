package com.acme.web.services.user.domain.model.entities;

import com.acme.web.services.user.domain.model.aggregates.User;
import com.acme.web.services.user.domain.model.commands.CreateAdvisorCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Advisor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String occupation;
    @NotNull
    private int experience;
    @NotNull
    private String photo;
    @NotNull
    private int rating;

    public Advisor(String occupation, int experience, String photo, int rating, User user) {
        this.occupation = occupation;
        this.experience = experience;
        this.photo = photo;
        this.rating = rating;
        this.user = user;
    }

    public Advisor() {}
}
