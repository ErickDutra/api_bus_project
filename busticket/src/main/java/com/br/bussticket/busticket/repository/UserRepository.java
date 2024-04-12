package com.br.bussticket.busticket.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.br.bussticket.busticket.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}