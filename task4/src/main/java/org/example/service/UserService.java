package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.model.entity.Users;
import org.example.reposiory.UsersRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;

    @Transactional
    public Users getUserById(Long id) throws SQLException {
        return usersRepository.findById(id).orElseThrow(() -> new SQLException("User not found"));
    }

    @Transactional
    public Users getUserByUsername(String username) throws SQLException {
        return usersRepository.findByUsername(username).orElseThrow(() -> new SQLException("User not found"));
    }

    public void deleteUserById(Long id) throws SQLException {
        usersRepository.deleteById(id);
    }

    public void deleteUserByUserName(String userName) throws SQLException {
        usersRepository.deleteByUsername(userName);
    }

    @Transactional
    public List<Users> getAllUsers() throws SQLException {
        return usersRepository.findAll();
    }

    public void deleteAllUsers() throws SQLException {
        usersRepository.deleteAll();
    }

    public void updateUser(Users user) throws SQLException {
        usersRepository.save(user);
    }
}
