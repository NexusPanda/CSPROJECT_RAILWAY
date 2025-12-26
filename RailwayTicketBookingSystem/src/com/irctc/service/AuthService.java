package com.irctc.service;


import com.irctc.dao.UserDAO;
import com.irctc.dao.impl.UserDAOImpl;
import com.irctc.model.User;

public class AuthService {

    private UserDAO userDAO = new UserDAOImpl();

    public int login(String email, String password) {
        return userDAO.login(email, password);
    }

    public boolean register(String name, String email, String password) {
        return userDAO.register(new User(name, email, password));
    }
}
