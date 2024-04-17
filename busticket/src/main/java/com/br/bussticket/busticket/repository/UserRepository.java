package com.br.bussticket.busticket.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.br.bussticket.busticket.model.User;

@Service
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String login);
    Optional<User> findByEmailOrderByName(String email);
}