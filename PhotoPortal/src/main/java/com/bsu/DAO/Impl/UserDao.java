package com.bsu.DAO.Impl;

import com.bsu.DAO.Dao;
import com.bsu.model.User;
import com.bsu.service.Impl.ConnectionPoolImpl;
import com.mysql.jdbc.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao implements Dao<User> {
    private Connection connection;
    public UserDao(){
        this.connection = ConnectionPoolImpl.getPool().getConnection();
    }
    public Connection getConnection() {
        return connection;
    }

    @Override
    public User get(long id) {
        String sql = "SELECT * FROM user " +
                "WHERE user_id = (?);";
        User user = new User();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            if(!rs.next())
                return null;
            user.setUserName(rs.getString("name"));
            user.setPasswordHash(rs.getString("hash"));
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getByLogin(String name) {
        String sql = "SELECT * FROM user " +
                "WHERE name = (?);";
        User user = new User();
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            if(!rs.next())
                return null;
            user.setUserName(rs.getString("name"));
            user.setPasswordHash(rs.getString("hash"));
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM user;";
        List<User> list = new ArrayList<>();

        try (PreparedStatement stm = connection.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setUserName(rs.getString("name"));
                user.setPasswordHash(rs.getString("hash"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean save(User user) {
        String sql = "INSERT INTO user(name, hash) " +
                "VALUES (?, ?);";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, user.getUserName());
            stm.setString(2, user.getPasswordHash());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(long id, Map<String, String> params) {
        // TO DO
        return true;
    }

    @Override
    public boolean delete(User user) {
        String sql = "DELETE FROM user WHERE user.name = (?);";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, user.getUserName());
            stm.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
