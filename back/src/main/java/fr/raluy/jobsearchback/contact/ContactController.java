package fr.raluy.jobsearchback.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "/contact")
    public List<Contact> getContacts (){
        return contactService.getAllContacts();
    }

    @PostMapping(value = "/contact")
    public ResponseEntity<String> addContact (@RequestBody Contact contact){
        contactService.add(contact);
        return new ResponseEntity<> (HttpStatus.OK);
    }

    @DeleteMapping(value = "/contact/{contactId}")
    public ResponseEntity<String> removeContact (@PathVariable("contactId") Integer contactId){
        contactService.removeById(contactId);
        return new ResponseEntity<> (HttpStatus.OK);
    }
}
