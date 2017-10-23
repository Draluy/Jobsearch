package fr.raluy.jobsearchback.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    @JsonProperty ("id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Please provide a name")
    @JsonProperty ("name")
    private String name;

    @OneToOne
    @JsonProperty ("address")
    private Address address;

    @Column(name = "phone")
    @JsonProperty ("phone")
    private String phone;

    @Column(name = "email")
    @Email(message = "Please provide a valid email")
    @JsonProperty ("email")
    private String email;

    @Column(name = "website")
    @URL(message = "Please provide a valid website url")
    @JsonProperty ("website")
    private String website;

}