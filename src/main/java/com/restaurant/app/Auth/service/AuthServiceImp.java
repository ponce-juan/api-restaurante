package com.restaurant.app.Auth.service;

import com.restaurant.app.Auth.dto.LoginRequest;
import com.restaurant.app.User.entity.User;
import com.restaurant.app.User.repository.UserRepository;
import com.restaurant.app.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AuthServiceImp implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

/// Log in user
    @Override
    public String login (@NonNull LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if(user == null)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password incorrect.");

        //Existe usuario y la clave coincide, genero jwt
        return jwtService.generateToken(user);
    }
}
