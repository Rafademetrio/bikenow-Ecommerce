package com.rafademetrio.bikenow_user.controllers;

import com.rafademetrio.bikenow_user.reposirories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    private UserRepository repository;


   
}
