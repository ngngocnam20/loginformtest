package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email);
        if (user == null)
        {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(user);
    }

    public User get(long id) throws UserNotFoundException
    {
        Optional<User> result = repository.findById(id);
        if (result.isPresent())
        {
            return  result.get();
        }
        throw new UserNotFoundException("Không tìm thấy người dùng");
    }
}
