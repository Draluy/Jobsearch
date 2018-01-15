package fr.raluy.jobsearchback.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.raluy.jobsearchback.auth.User;
import fr.raluy.jobsearchback.company.Company;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    private LocalDate date;

    @Column(name = "status")
    @JsonProperty ("status")
    private ApplicationStatus status;

    public void setUser(User user) {
        this.user = user;
    }
}
