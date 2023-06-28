package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.model.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface UserService {
    User getUserFromUserDtoAndRoles(UserDto userDto, Set<Role> role);

    User save(User user);

    void update(User user);

    List<User> findAll();

    void delete(Long id);

    User findById(Long id);

    User findUserByEmail(String email);
}
