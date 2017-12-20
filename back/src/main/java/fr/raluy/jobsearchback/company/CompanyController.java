package fr.raluy.jobsearchback.company;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController

public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/company")
    public List<Company> getCompanies (Authentication authentication){
        final String email = ((User)authentication.getPrincipal()).getUsername();
        return companyService.getAllCompanies(email);
    }

    @PostMapping(value = "/company")
    public ResponseEntity<String> addCompany (@RequestBody Company company, Authentication authentication){
        final String email = ((User)authentication.getPrincipal()).getUsername();
        companyService.add(company, email);
        return new ResponseEntity<> (HttpStatus.OK);
    }

    @DeleteMapping(value = "/company/{companyId}")
    public ResponseEntity<String> removeCompany (@PathVariable("companyId") Long companyId,
            Authentication authentication){
        final String email = ((User)authentication.getPrincipal()).getUsername();
        companyService.removeById(companyId, email);
        return new ResponseEntity<> (HttpStatus.OK);
    }
}
