package com.br.bussticket.busticket.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.bussticket.busticket.model.User;
import com.br.bussticket.busticket.repository.UserRepository;

@Service
public class UserService {
     @Autowired
     private UserRepository repo;

     public User getUserPorEmail(String email) {
          Optional<User> user = this.repo.findByEmailOrderByName(email);
          if(user.isPresent())
               return user.get();
          return new User();
     }
     //User create(User user);
}

