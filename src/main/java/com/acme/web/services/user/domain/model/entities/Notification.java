package com.acme.web.services.user.domain.model.entities;

import com.acme.web.services.user.domain.model.aggregates.User;
import com.acme.web.services.user.domain.model.commands.CreateNotificationCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String type;
    @NotNull
    private String text;
    @NotNull
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Notification(String type, String text, Date date, User user) {
        this.type = type;
        this.text = text;
        this.date = date;
        this.user = user;

    }

    public Notification() {}
}
