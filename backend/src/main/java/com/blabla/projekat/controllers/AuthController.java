package com.blabla.projekat.controllers;


import com.blabla.projekat.dto.AuthReponse;
import com.blabla.projekat.dto.AuthRequest;
import com.blabla.projekat.dto.SignUpRequest;
import com.blabla.projekat.dto.UserDTO;
import com.blabla.projekat.entities.User;
import com.blabla.projekat.repositories.UserRepository;
import com.blabla.projekat.services.auth.AuthService;
import com.blabla.projekat.services.user.UserService;
import com.blabla.projekat.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;

    private final JWTUtil jwtUtil;

    private final UserService userService;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(AuthService authService, JWTUtil jwtUtil, UserService userService, UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest)
    {
        if (authService.hasUserWithEmail(signUpRequest.getEmail()))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("USER ALREADY EXISTS");
        UserDTO userDTO = authService.signup(signUpRequest);
        if (userDTO == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }


    @PostMapping("/login")
    public AuthReponse login(@RequestBody AuthRequest authRequest) {
        try {
            System.out.println("Attempting login for user: " + authRequest.getEmail());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                    authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            System.out.println("Authentication failed for user: " + authRequest.getEmail());

            throw new BadCredentialsException("Incorrect username or pw");
        }
        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(authRequest.getEmail());
        Optional<User> optionalUser = userRepository.findFirstByEmail(authRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthReponse response = new AuthReponse();

        if (optionalUser.isPresent()) {
            response.setJwt(jwt);
            response.setUserRole(optionalUser.get().getUserRole());
            response.setUserId(optionalUser.get().getId());

        }
        return response;

    }
}

