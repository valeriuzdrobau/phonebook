package com.zdrobau.phonebook.contact;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "contacts")
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @GetMapping
    public ResponseEntity findAll(){
        logger.info("All contacts:");
        List<Contact> contacts = contactService.findAll();
        contacts.stream().map(Contact::toString).forEach(logger::info);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Contact contact){
        return new ResponseEntity<>(contactService.save(contact), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity edit(@RequestBody Contact contact){
        return new ResponseEntity<>(contactService.edit(contact), HttpStatus.CREATED);
    }

}
