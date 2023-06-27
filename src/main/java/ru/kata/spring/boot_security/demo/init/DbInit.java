package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DbInit {
    private final UserService userService;

    private final RoleService roleService;

    private static final String ADMIN_ROLE_NAME = "ADMIN";

    private static final String USER_ROLE_NAME = "USER";

    private static final String ADMIN_EMAIL = "admin@test.ru";

    private static final String USER_EMAIL = "user@test.ru";

    @Autowired
    public DbInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void createTestUsers() {
        Role roleAdmin = roleService.findRole(ADMIN_ROLE_NAME);
        if (roleAdmin == null) {
            roleAdmin = roleService.save(new Role(ADMIN_ROLE_NAME));
        }
        Role roleUser = roleService.findRole(USER_ROLE_NAME);
        if (roleUser == null) {
            roleUser = roleService.save(new Role(USER_ROLE_NAME));
        }

        User admin = userService.findUserByEmail(ADMIN_EMAIL);
        if (admin == null) {
            userService.save(new User("admin", "test", 1, ADMIN_EMAIL, "admin", Set.of(roleAdmin)));
        }
        User normalUser = userService.findUserByEmail(USER_EMAIL);
        if (normalUser == null) {
            userService.save(new User("user", "test", 2, USER_EMAIL, "user", Set.of(roleUser)));
        }
    }
}
