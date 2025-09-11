package org.example.service;

import org.example.dao.UserDao;
import org.example.model.Users;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public Users getUserById(Long id) throws SQLException {
        return userDao.getUserById(id);
    }

    public Users getUserByUsername(String username) throws SQLException {
        return userDao.getUserByUserName(username);
    }

    public void insertUser(Users users) throws SQLException {
        userDao.createUser(users);
    }

    public void updateUser(Users users) throws SQLException {
        userDao.updateUser(users);
    }

    public void deleteUser(Long id) throws SQLException {
        userDao.deleteUser(id);
    }

    public List<Users> getAllUsers() throws SQLException {
        return userDao.getUserAll();
    }

    public void clearSequences() throws SQLException {
        userDao.clearSequences();
    }

    public void deleteAllUsers() throws SQLException {
        userDao.deleteAllUsers();
    }
}
