package com.bsu.service.Impl;

import com.bsu.DAO.Impl.UserDao;
import com.bsu.exception.LoginException;
import com.bsu.exception.PasswordException;
import com.bsu.model.User;
import com.bsu.service.LoginService;

public class DBLoginServiceImpl implements LoginService {
    private UserDao userDao;

    public DBLoginServiceImpl() {
        this.userDao = new UserDao();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public User loginUser(String login, String password) throws LoginException, PasswordException {
        User user = userDao.getByLogin(login);
        if(user == null)
            throw new LoginException("Wrong username");

        if(LoginService.isHashEqual(password, user.getPasswordHash()))
            return user;
        else
            throw  new PasswordException("Wrong password");
    }

    @Override
    public boolean registerUser(User user) {
        return userDao.save(user);
    }

}
