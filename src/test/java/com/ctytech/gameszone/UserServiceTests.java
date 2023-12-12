package com.ctytech.gameszone;

import com.ctytech.gameszone.constants.UserStatus;
import com.ctytech.gameszone.dto.UserDTO;
import com.ctytech.gameszone.entity.Role;
import com.ctytech.gameszone.entity.User;
import com.ctytech.gameszone.exception.GameszoneException;
import com.ctytech.gameszone.repository.UserRepository;
import com.ctytech.gameszone.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Register User Test: Invalid Test (UserName Already Exits")
    void  registerUserInvalidTest1() {

        // Existing user with username "testUser1"
        Optional<User> testUser1 = mockUsersList().stream().filter(u -> u.getUserId().equals(1001)).findFirst();

        UserDTO newUser = new UserDTO();
        newUser.setUserName("testUser1");

        when(userRepository.findByUserName(Mockito.anyString())).thenReturn(testUser1);
        GameszoneException exception = Assertions.assertThrows(GameszoneException.class, () -> userService.registerNewUser(newUser));
        Assertions.assertEquals("UserService.USERNAME_ALREADY_EXISTS", exception.getMessage());
    }

    @Test
    @DisplayName("Fetch User by ID : Valid Test")
    void getUserByIdValidTest() throws GameszoneException {
        Optional<User> user = mockUsersList().stream().filter(u -> u.getUserId().equals(1001)).findFirst();
        when(userRepository.findById(Mockito.anyInt())).thenReturn(user);
        Assertions.assertEquals(1001, userService.getUser(1001).getUserId());
    }

    @Test
    @DisplayName("Fetch User by ID : Invalid Test")
    void getUserByIdInvalidTest() {
        when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        GameszoneException exception = Assertions.assertThrows(GameszoneException.class,
                () -> userService.getUser(1));
        Assertions.assertEquals("UserService.USER_NOT_FOUND", exception.getMessage());
    }


    @Test
    @DisplayName("Fetch User by Username : Valid Test")
    void getUserByUsernameValidTest() throws GameszoneException {
        Optional<User> user = mockUsersList().stream().filter(u -> u.getUserName().equalsIgnoreCase("testUser1")).findFirst();
        when(userRepository.findByUserName(Mockito.anyString())).thenReturn(user);
        Assertions.assertEquals("testUser1", userService.getUser("testUser1").getUserName());
    }

    @Test
    @DisplayName("Fetch User by Username : Invalid Test")
    void  getUsernameInvalidTest() {
        when(userRepository.findByUserName(Mockito.anyString())).thenReturn(Optional.empty());
        GameszoneException exception = Assertions.assertThrows(GameszoneException.class, () -> userService.getUser("testUser"));
        Assertions.assertEquals("UserService.USER_NOT_FOUND", exception.getMessage());
    }

    @Test
    @DisplayName("Fetch User by Email ID : Valid Test")
    void getUserByEmailValidTest() throws  GameszoneException {
        Optional<User> user = mockUsersList().stream().filter(u -> u.getEmail().equalsIgnoreCase("testUser1@gmail.com")).findFirst();
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);
        Assertions.assertEquals("testUser1@gmail.com", userService.getUserByEmail("testUser1@gmail.com").getEmail());
    }

    @Test
    @DisplayName("Fetch User by Email ID : Invalid Test")
    void getUserByEmailInvalidTest() {
        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        GameszoneException exception = Assertions.assertThrows(GameszoneException.class, () -> userService.getUserByEmail("testU@gamil.com"));
        Assertions.assertEquals("UserService.USER_NOT_FOUND", exception.getMessage());
    }


    private List<User> mockUsersList() {

        List<User> users = new ArrayList<>();

        // admin role
        Role adminRole = new Role();
        adminRole.setRoleId(1001);
        adminRole.setRoleName("ADMIN");
        // user role
        Role userRole = new Role();
        userRole.setRoleId(1002);
        userRole.setRoleName("User");

        // User 1001
        User user1001 = new User();
        user1001.setUserId(1001);
        user1001.setUserName("testUser1");
        user1001.setEmail("testUser1@gmail.com");
        user1001.setStatus(UserStatus.ACTIVE);
        user1001.setRoles(new HashSet<>(Arrays.asList(adminRole, userRole)));
        user1001.setPassword("testPassword");

        users.add(user1001);

        return users;

    }
}
