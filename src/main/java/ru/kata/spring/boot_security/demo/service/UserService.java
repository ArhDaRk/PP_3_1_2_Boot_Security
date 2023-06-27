package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.model.dto.UserDto;

import java.util.List;

public interface UserService {
    User save(User user);

    void update(User user);

    List<User> findAll();

    void delete(Long id);

    User findById(Long id);

    User findUserByEmail(String email);
}
