package fr.raluy.jobsearchback.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.raluy.jobsearchback.auth.User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    @JsonProperty ("id")
    private Long id;

    @ManyToOne
    private User user;

    @Column(name = "name")
    @NotEmpty(message = "Please provide a name")
    @JsonProperty ("name")
    private String name;

    @OneToOne (cascade = CascadeType.ALL)
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
    @JsonProperty ("website")
    private String website;

    public void setUser(User user) {
        this.user = user;
    }
}
