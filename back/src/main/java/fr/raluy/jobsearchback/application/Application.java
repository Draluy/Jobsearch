package fr.raluy.jobsearchback.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.raluy.jobsearchback.company.Address;
import fr.raluy.jobsearchback.company.Company;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    @JsonProperty ("id")
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Please provide a title")
    @JsonProperty ("title")
    private String title;

    @ManyToOne (optional = false)
    @JsonProperty ("company")
    private Company company;

    @Column(name = "date")
    @JsonProperty ("date")
    private LocalDate date;

    @Column(name = "status")
    @JsonProperty ("status")
    private ApplicationStatus status;
}
