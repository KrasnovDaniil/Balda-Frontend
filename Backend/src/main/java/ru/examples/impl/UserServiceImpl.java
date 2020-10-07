package ru.examples.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.examples.models.User;
import ru.examples.repositories.UserRepository;
import ru.examples.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ApplicationContext context;

    public UserServiceImpl(UserRepository userRepository, ApplicationContext context) {
        this.userRepository = userRepository;
        this.context = context;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    @Cacheable("users")
    public User get(Long id) {
        System.out.println("get user: " + id);
        return userRepository.findUserById(id);
    }

    @Override
    @Cacheable("users")
    public User getByName(String firstname) {
        System.out.println("get user with name " + firstname);
        return userRepository.findByFirstName(firstname);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
