package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.entity.Users;
import org.example.reposiory.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RunService implements CommandLineRunner {

    private final UserService userService;
    private final UsersRepository usersRepository;


    @Override
    @Transactional
    public void run(String... args) throws SQLException {
        log.info("Запуск тестов методов реозитория...");

        log.info("getUserById : {}", userService.getUserById(1L).toString());
        log.info("getUserByUserName : {}", userService.getUserByUsername("beta").toString());
        List<Users> users = usersRepository.findAll();
        log.info("findAll :");
        for (Users user : users) {
            log.info("user : {}", user.toString());
        }
    }
}
