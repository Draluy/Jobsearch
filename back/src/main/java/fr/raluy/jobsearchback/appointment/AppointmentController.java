package fr.raluy.jobsearchback.appointment;

import fr.raluy.jobsearchback.application.Application;
import fr.raluy.jobsearchback.application.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class AppointmentController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping(value = "/application/{applicationId}/appointment")
    public Collection<Appointment> getAppointments(@PathVariable("applicationId") Long applicationId, Authentication authentication) {
        final String email = ((User) authentication.getPrincipal()).getUsername();
        final Application application = applicationService.getApplication(applicationId, email);
        return application.getAppointments();
    }

    @PostMapping(value = "/application/{applicationId}/appointment")
    public ResponseEntity<String> addapplication(@PathVariable("applicationId") Long applicationId, @RequestBody Appointment appointment, Authentication authentication) {
        final String email = ((User) authentication.getPrincipal()).getUsername();
        final Application application = applicationService.getApplication(applicationId, email);
        applicationService.addAppointment(application, appointment, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/application/{applicationId}/appointment/{appointmentId}")
    public ResponseEntity<String> addapplication(@PathVariable("applicationId") Long applicationId, @PathVariable("appointmentId") Long appointmentId, Authentication authentication) {
        final String email = ((User) authentication.getPrincipal()).getUsername();
        final Application application = applicationService.getApplication(applicationId, email);
        final Optional<Appointment> appointment = application.getAppointments().stream()
                .filter(appt -> appt.getId().equals(appointmentId)).findFirst();
        appointment.ifPresent((appt) -> applicationService.deleteAppointement(application, appt, email));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
