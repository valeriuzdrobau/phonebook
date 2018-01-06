package com.zdrobau.phonebook.contact;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "contacts", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value="contacts", description = "Data service operations on contacts", tags=("contacts"))
public class ContactController {

    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @ApiOperation(value = "View a list of available contacts", response = Contact.class, responseContainer = "List")
    @GetMapping
    public ResponseEntity findAll(){
        logger.info("All contacts:");
        List<Contact> contacts = contactService.findAll();
        contacts.stream().map(Contact::toString).forEach(logger::info);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity save(@RequestBody Contact contact){
        return new ResponseEntity<>(contactService.save(contact), HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Edit a existing contact", response = Contact.class)
    @PutMapping
    public ResponseEntity edit(@RequestBody Contact contact){
        logger.info("Modify resource {}", contact.getId());
        logger.info(contact.toString());
        return new ResponseEntity<>(contactService.edit(contact), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        logger.info("Delete resource {}", id);
        contactService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
