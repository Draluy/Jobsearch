package fr.raluy.jobsearchback.application;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.raluy.jobsearchback.appointment.Appointment;
import fr.raluy.jobsearchback.auth.User;
import fr.raluy.jobsearchback.company.Company;
import fr.raluy.jobsearchback.deserialization.DateDeserializer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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

    @OneToMany(cascade = {CascadeType.ALL})
    private Collection<Appointment> appointments = new ArrayList<>();

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

    @Column(name = "resume_file_name")
    @JsonProperty ("resume_file_name")
    private String resumeFileName;

    @Column(name = "cover_letter", length=100000)
    @Lob
    @JsonIgnore
    private byte[] coverLetter;

    @Column(name = "cover_letter_file_name")
    @JsonProperty ("cover_letter_file_name")
    private String coverLetterFileName;

    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setResumeFileName(final String resumeFileName) {
        this.resumeFileName = resumeFileName;
    }

    public void setCoverLetter(byte[] coverLetter) {
        this.coverLetter = coverLetter;
    }

    public void setCoverLetterFileName(String coverLetterFileName) {
        this.coverLetterFileName = coverLetterFileName;
    }

    public void add(Appointment appointment) {
        appointments.add(appointment);
    }

    public void remove(Appointment appointment) {
        appointments.removeIf(appt -> appt.getId().equals(appointment.getId()));
    }

    public void deleteCoverLetter() {
        this.coverLetter = null;
        this.coverLetterFileName = null;
    }

    public void deleteResume() {
        this.resume = null;
        this.resumeFileName = null;
    }
}
