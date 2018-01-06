package com.zdrobau.phonebook.user.service;

import com.zdrobau.phonebook.user.model.User;
import com.zdrobau.phonebook.user.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    public User findByUsername( String username ) throws UsernameNotFoundException {
        return userRepository.findByUsername( username );
    }

    public User findById( Long id ) throws AccessDeniedException {
        return userRepository.findById( id ).orElse(null);
    }

    public List<User> findAll() throws AccessDeniedException {
        return userRepository.findAll();
    }
}
