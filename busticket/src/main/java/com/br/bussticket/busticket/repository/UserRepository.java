package com.br.bussticket.busticket.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.br.bussticket.busticket.model.UserTable;

public interface UserRepository extends JpaRepository<UserTable, Long> {
    
}