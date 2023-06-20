package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserDetailService;


@Controller
@RequestMapping("/")
public class UserController {

    private final UserDetailService userDetailService;

    @Autowired
    public UserController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping("/admin")
    public String userTable(Model model, User user) {
        model.addAttribute("userList", userDetailService.findAll());
        return "admin";
    }

    @PostMapping("/admin")
    public String createUser(User user) {
        userDetailService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userDetailService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/update-user/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userDetailService.findById(id));
        return "update-user";
    }

    @PutMapping("/update-user")
    public String updateUser(User user) {
        userDetailService.update(user);
        return "redirect:/admin";
    }
}
