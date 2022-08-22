package fr.raluy.jobsearchback.application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.raluy.jobsearchback.application.files.CoverLetter;
import fr.raluy.jobsearchback.application.files.Resume;
import fr.raluy.jobsearchback.appointment.Appointment;
import fr.raluy.jobsearchback.auth.User;
import fr.raluy.jobsearchback.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Application> getAllApplications(String email){
        final User user = userRepository.findByEmail(email);
        final List<Application> applications = new ArrayList<>();
        if (user != null) {
            applications.addAll(applicationRepository.findAllByUserOrderByDateAsc(user));
        }
        return applications;
    }

    public Resume getResumeById(final Long applicationId, final String email){
        final User user = userRepository.findByEmail(email);
        Resume resume = null;
        if (user != null) {
            resume  = applicationRepository.getResumeByIdAndUser(applicationId, user);
        }
        return resume;
    }

    public CoverLetter getCoverLetterById(final Long applicationId, final String email){
        final User user = userRepository.findByEmail(email);
        CoverLetter coverLetter = null;
        if (user != null) {
            coverLetter  = applicationRepository.getCoverLetterByIdAndUser(applicationId, user);
        }
        return coverLetter;
    }

    public void add(Application application, String email) {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            application.setUser(user);
            if (application.getDate() == null) {
                application.setDate(LocalDate.now());
            }
            applicationRepository.save(application);
        }
    }

    public void addAppointment(Application application, Appointment appointment,  String email) {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            application.add(appointment);
            applicationRepository.save(application);
        }
    }

    public void deleteAppointement(Application application, Appointment appointment,  String email) {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            application.remove(appointment);
            applicationRepository.save(application);
        }
    }

    @Transactional
    public void removeById(Long applicationId, String email) {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            applicationRepository.deleteByIdAndUser(applicationId, user);
        }
    }

    public void removeResumeById(Long applicationId, String email) {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            Application application = applicationRepository.findByIdAndUser(applicationId, user);
            application.deleteResume();
            applicationRepository.save(application);
        }
    }


    public void removeCoverLetterById(Long applicationId, String email) {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            Application application = applicationRepository.findByIdAndUser(applicationId, user);
            application.deleteCoverLetter();
            applicationRepository.save(application);
        }
    }


    public Application getApplication(Long applicationId, String email) {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            return applicationRepository.findByIdAndUser(applicationId, user);
        }
        return null;
    }

    public void saveResumeByApplicationId(Long applicationId, String email, MultipartFile resume) throws IOException {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            Application application = applicationRepository.findByIdAndUser(applicationId, user);
            application.setResume(resume.getBytes());
            application.setResumeFileName(resume.getOriginalFilename());
            applicationRepository.save(application);
        }
    }

    public void saveCoverLetterByApplicationId(Long applicationId, String email, MultipartFile coverLetter) throws IOException {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            Application application = applicationRepository.findByIdAndUser(applicationId, user);
            application.setCoverLetter(coverLetter.getBytes());
            application.setCoverLetterFileName(coverLetter.getOriginalFilename());
            applicationRepository.save(application);
        }
    }
}
