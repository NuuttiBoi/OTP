package com.example.projectdemo.integration;

import com.example.projectdemo.model.RegistrationDao;
import com.example.projectdemo.model.User;
import com.example.projectdemo.model.UserDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddUserToDbTest {

  private RegistrationDao registrationDao;
  private UserDao userDao;

  @BeforeEach
  public void setUp() throws SQLException {
    // Initialize DAOs and test database
    registrationDao = new RegistrationDao();
    userDao = new UserDao();
    // Optionally reset database or initialize test schema
  }
  @Test
  public void testNewUser() throws SQLException {
    // Arrange
    String name = "Nuutti Turunen";
    String email = "nuuttifake@gmail.com";
    String password = "password";

    // Act: Insert user into the database
    registrationDao.insertRecord(name, email, password);

    /*
    // Assert: Verify the user is in the database
    User user = userDao.getUserByEmail(email);
    assertNotNull(user);
    assertEquals(name, user.getName());
    assertEquals(email, user.getEmail());

     */
  }
}
