package com.irctc.dao;


import com.irctc.model.User;

public interface UserDAO {
    boolean register(User user);
    int login(String email, String password);
}
