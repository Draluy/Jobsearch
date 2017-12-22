package fr.raluy.jobsearchback.contact;

import java.util.ArrayList;
import java.util.List;

import fr.raluy.jobsearchback.auth.User;
import fr.raluy.jobsearchback.auth.UserRepository;
import fr.raluy.jobsearchback.company.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Contact> getAllContacts(String email){
        final User user = userRepository.findByEmail(email);
        final List<Contact> contacts = new ArrayList<>();

        if (user != null) {
            contacts.addAll(contactRepository.findAllByUserOrderByFirstnameAscLastnameAsc(user));
        }

        return contacts;
    }

    public void add(Contact contact, String email) {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            contact.setUser(user);
            contactRepository.save(contact);
        }
    }

    @Transactional
    public void removeById(Long contactId, String email) {
        final User user = userRepository.findByEmail(email);
        if (user != null) {
            contactRepository.deleteByIdAndUser(contactId, user);
        }
    }
}
