package fr.raluy.jobsearchback.application;

import java.io.*;
import java.net.URLConnection;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;

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
    public ResponseEntity<String> addapplication(@RequestParam("application") String applicationJson, @RequestParam(value = "resume", required = false) MultipartFile resume, Authentication authentication) throws IOException {
        final Application application = new ObjectMapper().readValue(applicationJson, Application.class);
        if (resume != null) {
            application.setResume(resume.getBytes());
            application.setResumeFileName(resume.getOriginalFilename());
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

    @GetMapping(value = "/application/{applicationId}/resume")
    public ResponseEntity<byte[]> getApplicationResume(@PathVariable("applicationId") Long applicationId, Authentication authentication) throws IOException, TikaException {
        final String email = ((User) authentication.getPrincipal()).getUsername();
        Resume resume = applicationService.getResumeById(applicationId, email);
        HttpHeaders headers = new HttpHeaders();
        org.apache.tika.mime.MediaType mediaType = getMediaType(resume);
        headers.set("Content-Type", mediaType.toString());
        headers.set("Content-Disposition","attachment; filename="+resume.getResumeFileName());
        return new ResponseEntity<>(resume == null ? null : resume.getResume(), headers, HttpStatus.OK);
    }

    private org.apache.tika.mime.MediaType getMediaType(Resume resume) throws TikaException, IOException {
        TikaConfig tika = new TikaConfig();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.RESOURCE_NAME_KEY, resume.getResumeFileName());
        return tika.getDetector().detect(
                TikaInputStream.get(resume.getResume()), metadata);
    }
}
