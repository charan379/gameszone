package com.ctytech.gameszone.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctytech.gameszone.dto.UserDTO;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/user")
@Validated
public class UserAPI {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> postNewUser(@RequestBody @Valid UserDTO userDTO) throws GameszoneException {
        String result = userService.registerNewUser(userDTO);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

}
