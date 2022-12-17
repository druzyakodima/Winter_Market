package com.winter.market.core.service.user;

import com.winter.market.core.entities.Role;
import com.winter.market.core.entities.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String username);

    Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles);
}
