package org.example;

import org.example.model.Users;
import org.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@ComponentScan
@Configuration
public class Main {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        
        UserService userService = context.getBean(UserService.class);

        userService.clearSequences();
        userService.deleteAllUsers();
        userService.insertUser(new Users(1L, "Шумилин"));
        userService.insertUser(new Users(2L, "Женатов"));
        Users usersByFamily1 = userService.getUserByUsername("Шумилин");
        Users usersByFamily2 = userService.getUserByUsername("Женатов");
        System.out.println(userService.getUserById(usersByFamily1.getId()).toString());
        System.out.println(userService.getUserById(usersByFamily2.getId()).toString());
        userService.updateUser(new Users(usersByFamily2.getId(), "Женатов Ермек"));
        System.out.println(userService.getUserById(usersByFamily2.getId()).toString());
        userService.deleteUser(usersByFamily2.getId());
        userService.getAllUsers().forEach(System.out::println);

        context.close();
        
    }
}