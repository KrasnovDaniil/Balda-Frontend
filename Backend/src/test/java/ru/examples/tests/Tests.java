package ru.examples.tests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.examples.impl.UserServiceImpl;
import ru.examples.models.User;
import ru.examples.repositories.MessageRepository;
import ru.examples.repositories.UserRepository;
import ru.examples.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Tests  {

    @Autowired
    UserService userService;

    @Test
    public void test1(){
        String name = "Danil";
        User user = userService.create(new User(name, "Krasnov", "daniil@mail.ru"));

        User u1 = userService.getByName(name);
        User u2 = userService.getByName(name);

        Long idd = 2L;
        User u3 = userService.get(idd);
        User u4 = userService.get(idd);

    }
}
