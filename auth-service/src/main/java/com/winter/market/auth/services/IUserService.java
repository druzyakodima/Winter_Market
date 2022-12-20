package com.winter.market.auth.services;

import com.winter.market.auth.entities.Role;
import com.winter.market.auth.entities.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String username);

    Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles);
}
