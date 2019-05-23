package com.bsu.service;

import com.bsu.entity.User;

public interface UsersService {
    User loginUser(String login, String password);
    boolean registerUser(User user);
    String hashPassword(String password);
}
