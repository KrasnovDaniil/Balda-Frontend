package ru.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.examples.models.User;
import ru.examples.repositories.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {
//public class Application {
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
    /*
    * The backend is done, so all that remains is React part. This I will do tomorrow with new power
    * */
}
