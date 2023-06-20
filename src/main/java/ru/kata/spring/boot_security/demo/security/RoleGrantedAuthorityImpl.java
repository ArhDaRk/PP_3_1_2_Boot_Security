package ru.kata.spring.boot_security.demo.security;

import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.demo.model.User;

public class RoleGrantedAuthorityImpl implements GrantedAuthority {

    private final User user;

    public RoleGrantedAuthorityImpl(User user) {
        this.user = user;
    }


    @Override
    public String getAuthority() {
        return user.getRole().toString();
    }
}
