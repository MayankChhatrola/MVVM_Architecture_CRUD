package com.demo.blackbox.mvvmarchitecturecrud.repository;

import android.content.Context;

import com.demo.blackbox.mvvmarchitecturecrud.database.UserDao;
import com.demo.blackbox.mvvmarchitecturecrud.model.User;

import java.util.List;

public class UserRepository {
    private final UserDao userDao;

    public UserRepository(Context context) {
        userDao = new UserDao(context);
    }

    public void addUser(User user) {
        userDao.insertUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(int userId) {
        userDao.deleteUser(userId);
    }
}
