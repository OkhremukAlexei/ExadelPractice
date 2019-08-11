package com.bsu.DAO.Impl;

import com.bsu.DAO.Dao;
import com.bsu.model.User;

import java.util.List;
import java.util.Map;

public class UserDao implements Dao<User> {
    @Override
    public User get(long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean save(User user) {
        return true;
    }

    @Override
    public boolean update(long id, Map<String, Object> params) {
        return true;
    }

    @Override
    public boolean delete(User user) {
        return true;
    }
}
