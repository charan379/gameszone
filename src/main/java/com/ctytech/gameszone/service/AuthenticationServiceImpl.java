package com.ctytech.gameszone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.security.jwt.JwtService;

@Service(value = "AuthenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public String authenticate(String userName, String password) throws GameszoneException, UsernameNotFoundException {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, password));

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        //
        return jwtService.generateToken(userDetails);
    }

}
