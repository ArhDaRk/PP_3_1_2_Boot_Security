package ru.kata.spring.boot_security.demo.model.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private int age;

    private String email;

    private String password;

    private Set<String > roles = new HashSet<>();
}
