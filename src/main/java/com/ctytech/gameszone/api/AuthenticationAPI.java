package com.ctytech.gameszone.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctytech.gameszone.api.requests.AuthRequest;
import com.ctytech.gameszone.api.responses.AuthResponse;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.security.jwt.JwtService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/auth")
public class AuthenticationAPI {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private UserDetailsService userDetailsService;

    @PostMapping(value = "/generate-token")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody @Valid AuthRequest authRequest)
            throws Exception, GameszoneException, UsernameNotFoundException {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUserName());
        //
        String accessToken = jwtService.generateToken(userDetails);

        return new ResponseEntity<AuthResponse>(new AuthResponse(userDetails.getUsername(), accessToken),
                HttpStatus.OK);

    }

}
