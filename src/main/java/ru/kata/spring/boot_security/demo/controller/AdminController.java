package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.model.dto.UserDto;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/admin")
    public String getAdminPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("authUser", user);
        model.addAttribute("user", new UserDto());
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/admin";
    }

    @PostMapping("/admin")
    public String createUser(UserDto userDto) {
        Set<Role> roles = userDto.getRoles().stream().map(roleService::findRole)
                .collect(Collectors.toSet());
        User user = userService.getUserFromUserDtoAndRole(userDto, roles);
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/update-user/{id}")
    public String getUpdatePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("userDto", new UserDto());
        return "admin/update-user";
    }

    @PutMapping("/update-user")
    public String updateUser(UserDto userDto) {
        Set<Role> roles = userDto.getRoles().stream().map(roleService::findRole)
                .collect(Collectors.toSet());
        User user = userService.getUserFromUserDtoAndRole(userDto, roles);
        userService.update(user);
        return "redirect:/admin";
    }
}