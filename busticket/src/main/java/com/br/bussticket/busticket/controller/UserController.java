package com.br.bussticket.busticket.controller;

import org.springframework.web.bind.annotation.*;
import com.br.bussticket.busticket.model.User;
import com.br.bussticket.busticket.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

}