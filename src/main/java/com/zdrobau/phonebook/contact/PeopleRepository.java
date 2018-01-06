package com.zdrobau.phonebook.contact;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "contacts", path = "people")
public interface PeopleRepository extends PagingAndSortingRepository<Contact, Long> {

    List<Contact> findByFirstNameIn(@Param("firstName") String firstName);

    List<Contact> findByLastNameIn(@Param("lastName") String lastName);

    List<Contact> findByPhoneNumberIn(@Param("phoneNumber") String firstName);
}
