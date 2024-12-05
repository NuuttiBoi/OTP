package com.example.projectdemo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for accessing users in the database.
 * @author Nuutti Turunen
 */
public class UserDao {
    private static final String SELECT_QUERY = "SELECT * FROM registration WHERE email_id = ? and password = ?";

    /**
     * Validates the user credentials.
     */
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


}
