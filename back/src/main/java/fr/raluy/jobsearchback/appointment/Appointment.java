package fr.raluy.jobsearchback.appointment;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.raluy.jobsearchback.contact.Contact;
import fr.raluy.jobsearchback.deserialization.DateDeserializer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    @JsonProperty ("id")
    private Long id;

    @Column(name = "date")
    @JsonProperty("date")
    @JsonDeserialize(using = DateDeserializer.class)
    private LocalDate date;

    @OneToOne
    @JsonProperty("contact")
    private Contact contact;

    public Long getId() {
        return id;
    }
}
