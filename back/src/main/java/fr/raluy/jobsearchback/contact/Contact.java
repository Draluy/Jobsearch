package fr.raluy.jobsearchback.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.raluy.jobsearchback.company.Company;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    @JsonProperty ("id")
    private int id;

    @Column(name = "firstname")
    @NotEmpty(message = "Please provide a first name")
    @JsonProperty ("firstname")
    private String firstname;

    @Column(name = "lastname")
    @JsonProperty ("lastname")
    private String lastname;

    @Column(name = "phone")
    @JsonProperty ("phone")
    private String phone;

    @ManyToOne
    @JsonProperty ("company")
    @NotNull(message = "Please specify a company")
    private Company company;
}
