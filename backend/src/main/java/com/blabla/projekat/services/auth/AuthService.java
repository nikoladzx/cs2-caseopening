package com.blabla.projekat.services.auth;

import com.blabla.projekat.dto.SignUpRequest;
import com.blabla.projekat.dto.UserDTO;

public interface AuthService {

    Boolean hasUserWithEmail(String email);
    UserDTO signup(SignUpRequest signUpRequest);
}
