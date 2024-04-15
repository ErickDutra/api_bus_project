package com.br.bussticket.busticket.dto;

import com.br.bussticket.busticket.role.Role;
public record RegisterDTO(String name ,Long cpf,String email,String password,Role role) {
}