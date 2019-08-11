package com.bsu.repository;

import com.bsu.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UsersSingleton {
    private List<User> users;
    private UsersSingleton() {
        this.users = new ArrayList<>(Arrays.asList(
                new User("Alex", "123"),
                new User("Natasha", "qwe"),
                new User("Dima", "python"),
                new User("Nikita", "moto")));
    }
    public List<User> getUsers(){
        return this.users;
    }
    public static class UsersHolder {
        public static final UsersSingleton HOLDER_INSTANCE = new UsersSingleton();
    }

    public static UsersSingleton getInstance() {
        return UsersSingleton.UsersHolder.HOLDER_INSTANCE;
    }
}
