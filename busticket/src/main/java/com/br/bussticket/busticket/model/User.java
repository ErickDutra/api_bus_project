package com.br.bussticket.busticket.model;

import java.util.List;

import com.br.bussticket.busticket.role.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_user;

    @Column(name = "name")
    private String name;
    @Column(name ="cpf")
    private Integer cpf;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    @ManyToMany
    private List<Role>roles;
}
