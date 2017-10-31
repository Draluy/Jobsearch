package fr.raluy.jobsearchback.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/company")
    public List<Company> getCompanies (){
        return companyService.getAllCompanies();
    }

    @PostMapping(value = "/company")
    public ResponseEntity<String> addCompany (@RequestBody Company company){
        companyService.add(company);
        return new ResponseEntity<> (HttpStatus.OK);
    }
}
