package com.ctytech.gameszone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ctytech.gameszone.entity.User;
import com.ctytech.gameszone.repository.UserRepository;
import com.ctytech.gameszone.utility.CustomUserDetails;

@Component(value = "userDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't recognize any user with provided username !"));

        return new CustomUserDetails(user.toDto());
    }

}
