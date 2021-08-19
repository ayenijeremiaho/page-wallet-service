package com.page_assessment_wallet_system.authentication.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ThisUserDetailsService implements UserDetailsService {

    //using static username and password as default
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User("page", "page", new ArrayList<>());
    }
}
