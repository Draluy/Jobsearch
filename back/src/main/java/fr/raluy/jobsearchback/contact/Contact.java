package fr.raluy.jobsearchback.contact;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.raluy.jobsearchback.auth.User;
import fr.raluy.jobsearchback.company.Company;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    @JsonProperty ("id")
    private Long id;

    @ManyToOne
    @NotNull(message = "Please specify a user")
    private User user;

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

    public void setUser(User user) {
        this.user = user;
    }
}
