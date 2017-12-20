package fr.raluy.jobsearchback.company;

import java.util.ArrayList;
import java.util.List;

import fr.raluy.jobsearchback.auth.User;
import fr.raluy.jobsearchback.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Company> getAllCompanies(final String email) {
        final User user = userRepository.findByEmail(email);
        final List<Company> companies = new ArrayList<>();
        if (user != null) {
            companies.addAll(companyRepository.findCompaniesByUserIdOrderByNameAsc(user.getId()));
        }
        return companies;
    }

    public void add(final Company company, final String email) {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            company.setUser(user);
            companyRepository.save(company);
        }
    }

    @Transactional
    public void removeById(Long companyId, final String email) {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            companyRepository.deleteByIdAndUser(companyId, user);
        }
    }
}
