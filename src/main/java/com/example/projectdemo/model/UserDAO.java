package com.example.projectdemo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final String SELECT_QUERY = "SELECT * FROM registration WHERE email_id = ? and password = ?";
    private static boolean isLoggedIn = false; // Track login state

    public User validate(String emailId, String password) throws SQLException {
        ConnectDb connectDb = new ConnectDb();
        Connection connection = connectDb.connect();

        // Single query to both validate and retrieve user details
        String sql = "SELECT * FROM registration WHERE email_id = ? AND password = ?";
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            // If a matching record is found, create a User object with the details
            if (resultSet.next()) {
                user = new User();
                user.setUserID(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email_id"));
                user.setPassword(resultSet.getString("password"));
            }
        }
        return user;
    }

    // Getter for login state
    public boolean isLoggedIn() {
        return isLoggedIn;
    }


    // Method to reset the login state (for logout)
    public static void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


    // Method to retrieve a User by userID from the database
    public User getUserByID(int userID) {
        User user = null;
        String sql = "SELECT * FROM registration WHERE id = ?";
        ConnectDb connectDb = new ConnectDb();
        try (Connection conn = connectDb.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userID);  // Set userID parameter

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserID(rs.getInt("id"));
                user.setEmail(rs.getString("email_id"));
                user.setRole("ok");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

}
