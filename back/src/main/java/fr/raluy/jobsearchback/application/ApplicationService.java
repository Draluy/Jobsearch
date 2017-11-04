package fr.raluy.jobsearchback.application;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public List<Application> getAllCompanies(){
        return applicationRepository.findAll(new Sort(Sort.Direction.ASC, "date"));
    }

    public void add(Application application) {
        if (!applicationRepository.existsById(application.getId())) {
            application.setDate(LocalDate.now());
        }

        applicationRepository.save(application);
    }

    public void removeById(int applicationId) {
        applicationRepository.deleteById(applicationId);
    }
}
