package org.example.dao;

import com.zaxxer.hikari.HikariDataSource;
import org.example.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {
    private final HikariDataSource hikariDataSource;

    @Autowired
    public UserDao(HikariDataSource hikariDataSource) {
        this.hikariDataSource = hikariDataSource;
    }

    public void createUser(Users users) throws SQLException {
        String sql = "insert into users (username) values (?)";
        try (Connection conn = hikariDataSource.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, users.getUsername());
            preparedStatement.executeUpdate();
        }
    }

    public Users getUserById(Long id) throws SQLException {
        String sql = "select * from users where id = ?";
        try (Connection conn = hikariDataSource.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return new Users(rs.getLong("id"), rs.getString("username"));
            }
        }

        return null;
    }

    public Users getUserByUserName(String username) throws SQLException {
        String sql = "select * from users where username = ?";
        try (Connection conn = hikariDataSource.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return new Users(rs.getLong("id"), rs.getString("username"));
            }
        }

        return null;
    }

    public void updateUser(Users users) throws SQLException {
        String sql = "update users set username = ? where id = ?";
        try(Connection conn = hikariDataSource.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, users.getUsername());
            preparedStatement.setLong(2, users.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteUser(Long id) throws SQLException {
        String sql = "delete from users where id = ?";
        try (Connection conn = hikariDataSource.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    public List<Users> getUserAll() throws SQLException {
        String sql = "select * from users";
        try (Connection conn = hikariDataSource.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            List<Users> users = new ArrayList<>();
            while (rs.next()) {
                users.add(new Users(rs.getLong("id"), rs.getString("username")));
            }
            return users;
        }
    }

    public void clearSequences() throws SQLException {
        String sql = "alter sequence users_id_seq start with 1;";
        try (Connection conn = hikariDataSource.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
    }


    public void deleteAllUsers() throws SQLException {
        String sql = "delete from users";
        try (Connection conn = hikariDataSource.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }
    }
}
