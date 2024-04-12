package com.br.bussticket.busticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.bussticket.busticket.model.User;
import com.br.bussticket.busticket.repository.UserRepository;

@Service
public class UserServiceImplement implements UserService {
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) {
        User existUser = userRepository.findByEmail(user.getEmail());
        if (existUser != null) {
            throw new Error("User already exists");
        }

        user.setPassword(passwordEncoder().encode(user.getPassword()));
        User createdUser = userRepository.save(user);
        return createdUser;
    }
}
