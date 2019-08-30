package com.bsu.model;

import org.json.simple.JSONObject;

public class User {
    private String username;
    private String passwordHash;

    public String getUserName() {
        return username;
    }
    public void setUserName(String login) {
        this.username = login;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("passwordHash", passwordHash);
        return json.toString();
    }
}
