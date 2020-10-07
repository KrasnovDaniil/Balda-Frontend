package ru.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import ru.examples.models.User;
import ru.examples.repositories.UserRepository;

@SpringBootApplication
@EnableCaching
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.save(new User("Daniil", "Krasnov", "daniil@mail.ru"));
        this.userRepository.save(new User("Timur", "Privetov", "timur@mail.ru"));
        this.userRepository.save(new User("Jan Ove", "Waldner", "waldner@mail.ru"));
    }
}
