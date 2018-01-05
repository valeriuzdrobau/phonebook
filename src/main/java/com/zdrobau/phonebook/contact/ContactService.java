package com.zdrobau.phonebook.contact;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContactService {

//    @PersistenceContext
//    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ContactRepository contactRepository;

    @Transactional(readOnly = true)
    public List<Contact> findAll(){
        return contactRepository.findAll();
    }

    @Transactional
    public Contact save(Contact contact){
        return contactRepository.save(contact);
    }

    @Transactional
    public Contact edit(Contact contact){
        Contact saved = contactRepository.findOne(contact.getId());

        //computations

        return contactRepository.save(contact);
    }
}
