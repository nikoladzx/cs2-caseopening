package com.blabla.projekat.services.auth;


import com.blabla.projekat.dto.SignUpRequest;
import com.blabla.projekat.dto.UserDTO;
import com.blabla.projekat.entities.User;
import com.blabla.projekat.enums.UserRole;
import com.blabla.projekat.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder
  ;  }
    @PostConstruct
    public void createAdminAccount(){
        Optional<User> userByRole = userRepository.findByUserRole(UserRole.ADMIN);
        if (userByRole.isEmpty()) {
            User user = new User();
            user.setName("Admin");
            user.setEmail("Admin.com");
            user.setUserRole(UserRole.ADMIN);
            user.setBalance(10000000000000000000000000.00);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Admin account created");
        }
        else System.out.println("Admin account already exists");

    }

    @Override
    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @Override
    public UserDTO signup(SignUpRequest signUpRequest) {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        String rawPassword = signUpRequest.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("Raw password: " + rawPassword);
        System.out.println("Encoded password: " + encodedPassword);
        user.setPassword(encodedPassword);
        user.setUserRole(UserRole.USER);

        return userRepository.save(user).getUserDTO();
    }
}
