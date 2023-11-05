package com.ctytech.gameszone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ctytech.gameszone.dto.UserDTO;
import com.ctytech.gameszone.entity.User;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.repository.UserRepository;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String registerNewUser(UserDTO userDTO) throws GameszoneException {
        
        User user = new User();
        System.out.println(userDTO);
        user.setUserName(userDTO.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        System.out.println(user);
        User newUser = userRepository.save(user);

        return "User registered successfully, with userName : " + newUser.getUserName();
    }

    @Override
    public UserDTO getUser(String username) throws GameszoneException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }

    @Override
    public UserDTO getUser(Integer userId) throws GameszoneException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }

    @Override
    public UserDTO getUserByEmail(String email) throws GameszoneException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserByEmail'");
    }

}
