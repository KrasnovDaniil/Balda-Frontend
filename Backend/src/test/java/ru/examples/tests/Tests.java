package ru.examples.tests;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.examples.models.User;
import ru.examples.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests  {

    @Autowired
    private UserRepository userRepo;

    // creating test data
    @Before
    public void setUp(){
//        this.userRepo.save(new User("Daniil", "Krasnov", "daniil@mail.ru"));
//        this.userRepo.save(new User("Timur", "Privetov", "timur@mail.ru"));
//        this.userRepo.save(new User("Marat", "Maratov", "marat@mail.ru"));
    }


    // first test: basic queries
    @Test
    public void testBasicQueries(){
        List list = userRepo.findAll();
        List<User> u1 = userRepo.findByFirstName("Daniil");
        List<User> u2 = userRepo.findByFirstName("Timur");

        Optional<User> u3 = userRepo.findById(2L);
        Optional<User> u4 = userRepo.findById(3L);
    }


    @Test
    public void testBadQueries(){
        List<User> nonexistentUser1 = userRepo.findByFirstName("Ivan");
        Optional<User> nonexistentUser2 = userRepo.findById(10L);
    }
}
