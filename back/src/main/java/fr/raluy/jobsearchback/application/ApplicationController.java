package fr.raluy.jobsearchback.application;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseEntity<String> addapplication(@RequestParam("application") String applicationJson, @RequestParam(value = "resume",required = false) MultipartFile resume, Authentication authentication) throws IOException {
        final Application application = new ObjectMapper().readValue(applicationJson, Application.class);
        if (resume != null) {
            application.setResume(resume.getBytes());
        }
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
