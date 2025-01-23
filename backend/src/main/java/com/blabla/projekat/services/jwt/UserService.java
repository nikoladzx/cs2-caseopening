package com.blabla.projekat.services.jwt;

import com.blabla.projekat.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();
    UserDTO getUser(Long id);
}
