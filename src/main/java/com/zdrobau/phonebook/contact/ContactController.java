package com.zdrobau.phonebook.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public ResponseEntity findAll(){
        return new ResponseEntity<>(contactService.findAll(), HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity save(@Valid @RequestBody Contact contact){
//        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
//    }

}
