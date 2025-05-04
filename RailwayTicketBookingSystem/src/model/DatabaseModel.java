package model;

import java.sql.*;

public class DatabaseModel {
    private static final String URL = "jdbc:postgresql://localhost:5434/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "JAVA";

    public static boolean createUser(UserModel user) {
        String query = "INSERT INTO UserDetails (Name, Email, Password) VALUES (?, ?, ?)";

        try (Connection connect = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connect.prepareStatement(query)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean loginUser(String email, String password) {
        String query = "SELECT Password FROM UserDetails WHERE Email = ?";

        try (Connection connect = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connect.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next() && result.getString("Password").equals(password)) {
                System.out.println("Login Successful!");
                return true;
            }
            System.out.println("Invalid Email or Password!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean adminUser(String email, String password) {
        String query = "SELECT Password FROM AdminTable WHERE Email = ?";

        try (Connection connect = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connect.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next() && result.getString("Password").equals(password)) {
                System.out.println("Login Successful!");
                return true;
            }
            System.out.println("Invalid Email or Password!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
