package ru.examples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.examples.models.User;
import ru.examples.repositories.UserRepository;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("users")
    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    @GetMapping("{userName}")
    public User getUser(@PathVariable String userName){
        return userRepository.findByFirstName(userName);
    }

}
