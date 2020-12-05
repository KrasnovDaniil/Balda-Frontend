package ru.examples.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.examples.models.User;
import ru.examples.repositories.UserRepository;

import java.util.List;

// This controller class purposes only for testing in Backend\src\test\java\ru\examples\tests\Tests.java
@RestController
@RequestMapping("api/get/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // get all users in repository
    @GetMapping("users")
    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    // get user by its id
    @GetMapping("user-by-id/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // get user by its firstName
    @GetMapping("user-by-firstname/{userName}")
    public List<User> getUser(@PathVariable String userName){
        return userRepository.findByFirstName(userName);
    }

}
