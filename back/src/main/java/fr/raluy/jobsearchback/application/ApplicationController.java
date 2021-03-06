package fr.raluy.jobsearchback.application;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.raluy.jobsearchback.application.files.CoverLetter;
import fr.raluy.jobsearchback.application.files.Resume;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController

public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping(value = "/application")
    public List<Application> getApplications(Authentication authentication) {
        final String email = ((User) authentication.getPrincipal()).getUsername();
        return applicationService.getAllApplications(email);
    }

    @GetMapping(value = "/application/{applicationId}")
    public Application getApplication(@PathVariable("applicationId") Long applicationId, Authentication authentication) {
        final String email = ((User) authentication.getPrincipal()).getUsername();
        return applicationService.getApplication(applicationId, email);
    }

    @PostMapping(value = "/application")
    public ResponseEntity<String> addapplication(@RequestParam("application") String applicationJson,
                                                 @RequestParam(value = "resume", required = false) MultipartFile resume,
                                                 @RequestParam(value = "coverLetter", required = false) MultipartFile coverLetter,
                                                 Authentication authentication) throws IOException {
        final Application application = new ObjectMapper().readValue(applicationJson, Application.class);
        application.setDate(LocalDate.now());
        if (resume != null) {
            application.setResume(resume.getBytes());
            application.setResumeFileName(resume.getOriginalFilename());
        }
        if (coverLetter != null) {
            application.setCoverLetter(coverLetter.getBytes());
            application.setCoverLetterFileName(coverLetter.getOriginalFilename());
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
        headers.set("Content-Disposition", "attachment; filename=" + resume.getResumeFileName());
        return new ResponseEntity<>(resume == null ? null : resume.getResume(), headers, HttpStatus.OK);
    }

    @GetMapping(value = "/application/{applicationId}/coverletter")
    public ResponseEntity<byte[]> getApplicationCoverLetter(@PathVariable("applicationId") Long applicationId, Authentication authentication) throws IOException, TikaException {
        final String email = ((User) authentication.getPrincipal()).getUsername();
        CoverLetter coverLetter = applicationService.getCoverLetterById(applicationId, email);
        HttpHeaders headers = new HttpHeaders();
        org.apache.tika.mime.MediaType mediaType = getMediaType(coverLetter);
        headers.set("Content-Type", mediaType.toString());
        headers.set("Content-Disposition", "attachment; filename=" + coverLetter.getCoverLetterFileName());
        return new ResponseEntity<>(coverLetter == null ? null : coverLetter.getCoverLetter(), headers, HttpStatus.OK);
    }

    @PostMapping(value = "/application/{applicationId}/resume")
    public void saveResume(@PathVariable("applicationId") Long applicationId,
                           Authentication authentication,
                           @RequestParam(value = "resume", required = false) MultipartFile resume) throws IOException {
        final String email = ((User) authentication.getPrincipal()).getUsername();
        applicationService.saveResumeByApplicationId(applicationId, email, resume);
    }

    @PostMapping(value = "/application/{applicationId}/coverletter")
    public void saveCoverLetter(@PathVariable("applicationId") Long applicationId,
                                Authentication authentication,
                                @RequestParam(value = "coverletter", required = false) MultipartFile coverletter) throws IOException {
        final String email = ((User) authentication.getPrincipal()).getUsername();
        applicationService.saveCoverLetterByApplicationId(applicationId, email, coverletter);
    }

    @DeleteMapping(value = "/application/{applicationId}/resume")
    public ResponseEntity<String> removeResume(@PathVariable("applicationId") Long applicationId, Authentication authentication) {
        final String email = ((User) authentication.getPrincipal()).getUsername();
        applicationService.removeResumeById(applicationId, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/application/{applicationId}/coverletter")
    public ResponseEntity<String> removeCoverLetter(@PathVariable("applicationId") Long applicationId, Authentication authentication) {
        final String email = ((User) authentication.getPrincipal()).getUsername();
        applicationService.removeCoverLetterById(applicationId, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private org.apache.tika.mime.MediaType getMediaType(Resume resume) throws TikaException, IOException {
        TikaConfig tika = new TikaConfig();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.RESOURCE_NAME_KEY, resume.getResumeFileName());
        return tika.getDetector().detect(
                TikaInputStream.get(resume.getResume()), metadata);
    }

    private org.apache.tika.mime.MediaType getMediaType(CoverLetter coverLetter) throws TikaException, IOException {
        TikaConfig tika = new TikaConfig();
        Metadata metadata = new Metadata();
        metadata.set(Metadata.RESOURCE_NAME_KEY, coverLetter.getCoverLetterFileName());
        return tika.getDetector().detect(
                TikaInputStream.get(coverLetter.getCoverLetter()), metadata);
    }
}
