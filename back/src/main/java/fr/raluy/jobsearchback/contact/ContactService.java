package fr.raluy.jobsearchback.contact;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts(){
        return contactRepository.findAll(new Sort(Sort.Direction.ASC, "firstname","lastname"));
    }

    public void add(Contact contact) {
        contactRepository.save(contact);
    }

    public void removeById(int contactId) {
        contactRepository.deleteById(contactId);
    }
}
