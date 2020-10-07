package ru.examples.service;

import ru.examples.models.User;

import java.util.List;

public interface UserService {
    User create(User user);

    User get(Long id);

    User getByName(String firstname);

    List<User> getAll();

}
