package fr.raluy.jobsearchback.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping(value = "/application")
    public List<Application> getCompanies(Authentication authentication) {
        final String email = ((User) authentication.getPrincipal()).getUsername();
        return applicationService.getAllApplications(email);
    }

    @PostMapping(value = "/application")
    public ResponseEntity<String> addapplication(@RequestBody Application application, Authentication authentication) {
        final String email = ((User) authentication.getPrincipal()).getUsername();
        applicationService.add(application, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/application/{applicationId}")
    public ResponseEntity<String> removeapplication(@PathVariable("applicationId") Long applicationId, Authentication authentication) {
        final String email = ((User) authentication.getPrincipal()).getUsername();
        applicationService.removeById(applicationId, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
