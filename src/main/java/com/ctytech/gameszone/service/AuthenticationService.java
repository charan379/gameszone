package com.ctytech.gameszone.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ctytech.gameszone.exception.GameszoneException;

public interface AuthenticationService {

    String authenticate(String userName, String password) throws GameszoneException, UsernameNotFoundException;

}
