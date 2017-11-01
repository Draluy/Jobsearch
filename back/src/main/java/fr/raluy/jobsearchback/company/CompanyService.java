package fr.raluy.jobsearchback.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies(){
        return companyRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    public void add(Company company) {
        companyRepository.save(company);
    }

    public void removeById(int companyId) {
        companyRepository.deleteById(companyId);
    }
}
