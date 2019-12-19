package com.seohan.general.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.seohan.general.Domain.User;
import com.seohan.general.Mapper.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByAsabn(username);
        UserDetails userDetails = null;
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
 
        org.springframework.security.core.userdetails.User.UserBuilder builder =
            org.springframework.security.core.userdetails.User.builder()
            .passwordEncoder(encoder::encode);
 
        if (user != null) {
            userDetails = builder.username(user.getAsabn())
                    .password(user.getPass())
                    .roles(user.getRole())
                    .build();
        }
 
        return userDetails;
    }
}