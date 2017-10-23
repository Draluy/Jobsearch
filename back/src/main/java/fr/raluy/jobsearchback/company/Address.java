package fr.raluy.jobsearchback.company;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    @JsonProperty("id")
    private int id;

    @Column(name = "address")
    @NotEmpty(message = "Please provide an adress")
    @JsonProperty("address")
    private String address;

    @Column(name = "postal_code")
    @JsonProperty("postalcode")
    private String postalCode;

    @Column(name = "city")
    @JsonProperty("city")
    private String city;
}
