package fr.raluy.jobsearchback.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.raluy.jobsearchback.auth.User;
import fr.raluy.jobsearchback.auth.UserRepository;
import fr.raluy.jobsearchback.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public void add(Application application, String email) {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            application.setUser(user);
            application.setDate(LocalDate.now());
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
}
