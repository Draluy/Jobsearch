package fr.raluy.jobsearchback.application;

import fr.raluy.jobsearchback.auth.User;
import fr.raluy.jobsearchback.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findAllByUserOrderByDateAsc (User user);
    void deleteByIdAndUser(Long applicationId, User user);
}