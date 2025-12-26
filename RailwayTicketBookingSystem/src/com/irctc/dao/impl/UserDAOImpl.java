package com.irctc.dao.impl;



import com.irctc.dao.UserDAO;
import com.irctc.model.User;
import com.irctc.util.DBConnection;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    public boolean register(User user) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users(name,email,password) VALUES(?,?,?)");
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public int login(String email, String password) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id FROM users WHERE email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (Exception e) {}
        return -1;
    }
}
