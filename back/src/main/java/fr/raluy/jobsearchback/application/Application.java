package fr.raluy.jobsearchback.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.raluy.jobsearchback.auth.User;
import fr.raluy.jobsearchback.company.Company;
import fr.raluy.jobsearchback.deserialization.DateDeserializer;

import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    @JsonProperty ("id")
    private Long id;

    @ManyToOne
    private User user;

    @Column(name = "title")
    @NotEmpty(message = "Please provide a title")
    @JsonProperty ("title")
    private String title;

    @ManyToOne (optional = false)
    @JsonProperty ("company")
    private Company company;

    @Column(name = "date")
    @JsonProperty ("date")
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDate date;

    @Column(name = "status")
    @JsonProperty ("status")
    private ApplicationStatus status;

    @Column(name = "resume", length=100000)
    @Lob
    @JsonIgnore
    private byte[] resume;

    public void setUser(User user) {
        this.user = user;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
