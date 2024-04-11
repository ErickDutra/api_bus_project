package com.br.bussticket.busticket.dto;

import lombok.Data;

@Data
public class UserDto {
    
    private Long id_user;
    private String nome;
    private Integer cpf ;
}
