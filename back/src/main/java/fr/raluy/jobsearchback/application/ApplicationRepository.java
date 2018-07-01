package fr.raluy.jobsearchback.application;

import fr.raluy.jobsearchback.application.files.CoverLetter;
import fr.raluy.jobsearchback.application.files.Resume;
import fr.raluy.jobsearchback.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findAllByUserOrderByDateAsc (User user);
    void deleteByIdAndUser(Long applicationId, User user);
    Resume getResumeByIdAndUser(Long applicationId, User user);
    CoverLetter getCoverLetterByIdAndUser(Long applicationId, User user);
    Application findByIdAndUser(Long applicationId, User user);
}