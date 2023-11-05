package com.ctytech.gameszone.service;

import com.ctytech.gameszone.dto.UserDTO;
import com.ctytech.gameszone.exception.GameszoneException;

public interface UserService {

    String registerNewUser(UserDTO userDTO) throws GameszoneException;

    UserDTO getUser(String username) throws GameszoneException;

    UserDTO getUser(Integer userId) throws GameszoneException;

    UserDTO getUserByEmail(String email) throws GameszoneException;
}
