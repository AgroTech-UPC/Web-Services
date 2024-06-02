package com.acme.web.services.publication.domain.model.aggregates;

import com.acme.web.services.publication.domain.model.commands.CreatePublicationCommand;
import com.acme.web.services.publication.domain.model.valueobjects.PublicationContent;
import com.acme.web.services.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

import java.util.Date;

@Getter
@Entity
public class Publication extends AuditableAbstractAggregateRoot<Publication> {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "title", column = @Column(name = "title")),
            @AttributeOverride(name = "description", column = @Column(name = "description")),
            @AttributeOverride(name = "image", column = @Column(name = "image"))
    })
    private PublicationContent publicationContent;

    private Date date;

    public Publication(String title, String description, String image, Date date) {
        this.publicationContent = new PublicationContent(title, description, image);
        this.date = date;
    }

    public Publication() {
    }

    public Publication(CreatePublicationCommand command) {
        this.publicationContent = new PublicationContent(command.title(), command.description(), command.image());
        this.date = command.date();
    }

    public void updatePublicationContent(String title, String description, String image) {
        this.publicationContent = new PublicationContent(title, description, image);
    }


}
