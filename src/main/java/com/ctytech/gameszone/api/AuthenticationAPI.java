package com.ctytech.gameszone.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctytech.gameszone.api.requests.AuthRequest;
import com.ctytech.gameszone.api.responses.AuthResponse;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.service.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/auth")
public class AuthenticationAPI {

        @Autowired
        private AuthenticationService authenticationService;

        @PostMapping(value = "/generate-token")
        public ResponseEntity<AuthResponse> generateToken(@RequestBody @Valid AuthRequest authRequest)
                        throws Exception, GameszoneException, UsernameNotFoundException {

                String accessToken = authenticationService.authenticate(authRequest.getUserName(),
                                authRequest.getPassword());

                AuthResponse authResponse = new AuthResponse(authRequest.getUserName(), accessToken);

                return new ResponseEntity<AuthResponse>(authResponse,
                                HttpStatus.OK);

        }

}
