package fr.raluy.jobsearchback.company;

import fr.raluy.jobsearchback.auth.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findByEmail(String email);
    List<Company> findCompaniesByUserIdOrderByNameAsc (Long userId);
    void deleteByIdAndUser(Long id, User user);
}