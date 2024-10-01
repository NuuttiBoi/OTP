package com.example.projectdemo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final String SELECT_QUERY = "SELECT * FROM registration WHERE email_id = ? and password = ?";

    public boolean validate(String emailId, String password) throws SQLException {

        ConnectDb connectDb = new ConnectDb();
        Connection connection = connectDb.connect();

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
        preparedStatement.setString(1, emailId);
        preparedStatement.setString(2, password);

        System.out.println(preparedStatement);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

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
