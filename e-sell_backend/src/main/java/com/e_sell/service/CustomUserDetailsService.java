package com.e_sell.service;

import com.e_sell.domain.UserInfo;
import com.e_sell.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the database
        UserInfo userEntity = userRepository.findByEmail(username);

        // If the user is not found, throw UsernameNotFoundException
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Convert the UserEntity to UserDetails
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())  // Ensure this is the hashed password
                .roles(userEntity.getRole())  // Assuming role is a string, consider converting to roles if needed
                .build();
    }
}
