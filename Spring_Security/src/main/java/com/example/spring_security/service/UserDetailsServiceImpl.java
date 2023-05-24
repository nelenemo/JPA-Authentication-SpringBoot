package com.example.spring_security.service;

import com.example.spring_security.model.User;
import com.example.spring_security.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=repository.findByUsername(username);
        CustomUserDetails userDetails=null;

        if(user !=null)
        {
            userDetails=new CustomUserDetails(user);
            userDetails.getUser();
        }
        else {
            throw new UsernameNotFoundException("user doesnt exist with this username:" +username);
        }

        return userDetails;
    }
}
