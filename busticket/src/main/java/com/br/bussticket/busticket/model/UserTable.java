package com.br.bussticket.busticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class UserTable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_user;

    private String nome;
    private Integer cpf ;
}
