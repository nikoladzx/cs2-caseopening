package com.blabla.projekat.services.user;

import com.blabla.projekat.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();
    UserDTO getUser(Long id);
    Boolean sellSkin(Long skinId, Long userId);
    Boolean addBalance(Long userId, Double balance);
}
