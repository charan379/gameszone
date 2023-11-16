package com.ctytech.gameszone.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ctytech.gameszone.constants.UserStatus;
import com.ctytech.gameszone.dto.UserDTO;
import com.ctytech.gameszone.entity.Role;
import com.ctytech.gameszone.entity.User;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.repository.RoleRepository;
import com.ctytech.gameszone.repository.UserRepository;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String registerNewUser(UserDTO userDTO) throws GameszoneException {

        // check if user already exits
        // check username
        Optional<User> username = userRepository.findByUserName(userDTO.getUserName());
        if (username.isPresent()) {
            throw new GameszoneException("UserService.USERNAME_ALREADY_EXISTS");
        }
        // check email
        Optional<User> email = userRepository.findByEmail(userDTO.getEmail());
        if (email.isPresent()) {
            throw new GameszoneException("UserService.EMAIL_ALREADY_EXIST");
        }

        // if user not exits then create new user
        User user = new User();
        Set<Role> roles = new HashSet<Role>();
        roles.add(
                roleRepository.findByRoleName("USER").orElseThrow(() -> new GameszoneException("Role Not Found")));
        //
        user.setUserName(userDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setStatus(UserStatus.ACTIVE);
        user.setRoles(roles);
        //
        // create newUser
        User newUser = userRepository.save(user);

        return "User registered successfully, with userName : " + newUser.getUserName();
    }

    @Override
    public UserDTO getUser(String username) throws GameszoneException {
        Optional<User> user = userRepository.findByUserName(username);

        if (user.isPresent()) {
            return user.get().toDto();
        } else {
            throw new GameszoneException("UserService.USER_NOT_FOUND");
        }
    }

    @Override
    public UserDTO getUser(Integer userId) throws GameszoneException {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get().toDto();
        } else {
            throw new GameszoneException("UserService.USER_NOT_FOUND");
        }
    }

    @Override
    public UserDTO getUserByEmail(String email) throws GameszoneException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            return user.get().toDto();
        } else {
            throw new GameszoneException("UserService.USER_NOT_FOUND");
        }
    }

}
