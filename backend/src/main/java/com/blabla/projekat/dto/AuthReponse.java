package com.blabla.projekat.dto;

import com.blabla.projekat.enums.UserRole;

public class AuthReponse {

    private String jwt;

    private long userId;

    private UserRole userRole;
    

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
