package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.security.UserDetailsImpl;

import java.util.List;
import java.util.Optional;


@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void update(User user) {
        Long id = user.getId();
        User updateUser = userRepository.findById(id).orElseThrow();
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setEmail(user.getEmail());
        updateUser.setAge(user.getAge());
        userRepository.save(updateUser);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(username);
        if (user.isEmpty()) throw new UsernameNotFoundException("User not found!");
        return new UserDetailsImpl(user.get());
    }
}
