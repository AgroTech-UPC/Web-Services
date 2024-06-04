package com.acme.web.services.user.domain.model.aggregates;

import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.web.services.user.domain.model.commands.CreateUserCommand;
import com.acme.web.services.user.domain.model.entities.Notification;
import com.acme.web.services.user.domain.model.valueobjects.*;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Entity
public class User extends AuditableAbstractAggregateRoot<User>{
    @Embedded
    @Column(name="email")
    private final EmailAddress emailAddress;
    @Embedded
    @Column(name="password")
    private final Password password;
    @Embedded
    @Column(name="fullname")
    private final Fullname fullname;

    @Embedded
    @Column(name="location")
    private final Location location;

    @Embedded
    @Column(name="birthdate")
    private final Birthdate birthdate;

    @Embedded
    @Column(name="description")
    private final Description description;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notifications;

    public User(){
        this.emailAddress = new EmailAddress();
        this.password = new Password();
        this.fullname = new Fullname();
        this.location = new Location();
        this.birthdate = new Birthdate();
        this.description = new Description();
        this.notifications = new ArrayList<>();
    }

    public User(CreateUserCommand command){
        this.emailAddress = new EmailAddress(command.email());
        this.password = new Password(command.password());
        this.fullname = new Fullname(command.fullname());
        this.location = new Location(command.location());
        this.birthdate = new Birthdate(command.birthdate());
        this.description = new Description(command.description());
    }

    public User(String email, String password, String fullname, String location, LocalDate birthdate, String description){
        this.emailAddress = new EmailAddress(email);
        this.password = new Password(password);
        this.fullname = new Fullname(fullname);
        this.location = new Location(location);
        this.birthdate = new Birthdate(birthdate);
        this.description = new Description(description);
    }

    public String getEmail(){
        return this.emailAddress.email();
    }

    public String getPassword(){
        return this.password.password();
    }

    public String getFullname(){
        return this.fullname.fullname();
    }

    public String getLocation(){
        return this.location.location();
    }

    public LocalDate getBirthdate(){
        return this.birthdate.birthdate();
    }

    public String getDescription(){
        return this.description.description();
    }


}
