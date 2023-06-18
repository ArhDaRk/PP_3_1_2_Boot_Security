package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/table")
    public String userTable(Model model, User user) {
        model.addAttribute("userList", userService.findAll());
        return "table";
    }

    @PostMapping("/table")
    public String createUser(User user) {
        userService.save(user);
        return "redirect:/table";
    }

    @DeleteMapping("/table/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/table";
    }

    @GetMapping("/update-user/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "update-user";
    }

    @PutMapping("/update-user")
    public String updateUser(User user) {
        userService.update(user);
        return "redirect:/table";
    }
}
