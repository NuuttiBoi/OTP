package com.example.projectdemo.model;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class RegistrationTest {
  @Test
  public void testInsertRecord() throws SQLException {
    // Arrange
    String name = "Test User";
    String email = "test@example.com";
    String password = "password";
    RegistrationDao dao = new RegistrationDao();

    // Act
    boolean result = dao.insertRecord(name, email, password);

    // Assert
    assertTrue(result);
  }
}
