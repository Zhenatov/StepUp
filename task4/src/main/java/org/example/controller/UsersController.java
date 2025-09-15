package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.Users;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public Users getUsers(@PathVariable("userId") Long userId) throws SQLException {
        return userService.getUserById(userId);
    }
}
