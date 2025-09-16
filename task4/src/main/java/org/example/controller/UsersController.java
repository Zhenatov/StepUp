package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.dto.UsersDto;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public UsersDto getUsers(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }
}
