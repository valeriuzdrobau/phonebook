package com.zdrobau.phonebook.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Transactional(readOnly = true)
    public List<Contact> findAll(){
        return contactRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Contact save(Contact contact){
        return contactRepository.saveAndFlush(contact);
    }

    @Transactional
    public Contact edit(Contact contact){
        Contact saved = contactRepository.getOne(contact.getId());

        saved.setFirstName(contact.getFirstName());
        saved.setLastName(contact.getLastName());
        saved.setPhoneNumber(contact.getPhoneNumber());

        return contactRepository.saveAndFlush(contact);
    }
}
