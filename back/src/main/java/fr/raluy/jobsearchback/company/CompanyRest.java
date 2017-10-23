package fr.raluy.jobsearchback.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CompanyRest {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/company")
    public List<Company> getCompanies (){
        return companyService.getAllCompanies();
    }
}
