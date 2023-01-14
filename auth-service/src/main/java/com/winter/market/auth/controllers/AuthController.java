package com.winter.market.auth.controllers;

import com.winter.market.api.dtos.AppError;
import com.winter.market.api.dtos.JwtRequest;
import com.winter.market.api.dtos.JwtResponse;
import com.winter.market.auth.entities.Role;
import com.winter.market.auth.entities.User;
import com.winter.market.auth.services.RoleService;
import com.winter.market.auth.utils.JwtTokenUtil;
import com.winter.market.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final RoleService roleService;
    private final JwtTokenUtil jwtTokenUtil;

    private final AuthenticationManager authenticationManager;
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError("CHECK_TOKEN_ERROR", "Некорректный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/create")
    public void createNewUser(@RequestBody User user) {
        userService.createNewUser(user);
    }

    @GetMapping("/roles")
    public List<Role> findAllRoles() {
        return roleService.findAll();
    }
}
