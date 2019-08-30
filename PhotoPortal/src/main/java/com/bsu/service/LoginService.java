package com.bsu.service;

import com.bsu.exception.LoginException;
import com.bsu.exception.PasswordException;
import com.bsu.model.User;
import org.apache.commons.codec.digest.DigestUtils;

public interface LoginService {
    User loginUser(String login, String password) throws LoginException, PasswordException;
    boolean registerUser(User user);
    static String getHash(String password) {
        return DigestUtils.md5Hex(password);
    }
    static boolean isHashEqual(String password, String passwordHash) {
        String hash = DigestUtils.md5Hex(password);
        return passwordHash.equals(hash);
    }
}
