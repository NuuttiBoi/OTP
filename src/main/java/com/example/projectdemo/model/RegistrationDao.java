package com.example.projectdemo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class for accessing registrations in the database.
 */
public class RegistrationDao {
    // Replace below database url, username and password with your actual database credentials
    private static final String INSERT_QUERY = "INSERT INTO registration (full_name, email_id, password) VALUES (?, ?, ?)";


    /**
     * Adds a new user to the database.
     */
    public void insertRecord(String fullName, String emailId, String password) throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        ConnectDb connectDb = new ConnectDb();
        Connection connection = connectDb.connect();

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
        preparedStatement.setString(1, fullName);
        preparedStatement.setString(2, emailId);
        preparedStatement.setString(3, password);

        System.out.println(preparedStatement);
        // Step 3: Execute the query or update query
        preparedStatement.executeUpdate();
    }

    /**
     * Method for printing exceptions.
     */
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
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
}
