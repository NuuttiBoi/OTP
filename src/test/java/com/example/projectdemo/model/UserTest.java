package com.example.projectdemo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

  private User user;

  @BeforeEach
  public void setUp() {
    user = new User(1, "test@example.com", "password123");
  }

  @Test
  public void testUserConstructor() {
    // Test constructor with parameters
    assertNotNull(user);
    assertEquals(1, user.getUserID());
    assertEquals("test@example.com", user.getEmail());
    assertEquals("password123", user.getPassword());
  }

  @Test
  public void testDefaultConstructor() {
    // Test default constructor
    User defaultUser = new User();
    assertNotNull(defaultUser);
    assertEquals(0, defaultUser.getUserID());  // Default value for int is 0
    assertNull(defaultUser.getEmail());
    assertNull(defaultUser.getPassword());
  }

  @Test
  public void testSettersAndGetters() {
    // Test setter and getter for userID
    user.setUserID(2);
    assertEquals(2, user.getUserID());

    // Test setter and getter for email
    user.setEmail("newemail@example.com");
    assertEquals("newemail@example.com", user.getEmail());

    // Test setter and getter for password
    user.setPassword("newpassword123");
    assertEquals("newpassword123", user.getPassword());

    // Test setter and getter for role
    user.setRole("admin");
    assertEquals("admin", user.getRole());

    // Test setter and getter for rentalID
    user.setRentalID("R123");
    assertEquals("R123", user.getRentalID());

    // Test setter and getter for type
    user.setType("premium");
    assertEquals("premium", user.getType());
  }

  @Test
  public void testToString() {
    user.setRole("admin");
    user.setType("premium");
    user.setRentalID("R123");

    String expectedToString = "User{userID=1, email='test@example.com', role='admin', type='premium', rentalID='R123'}";
    assertEquals(expectedToString, user.toString());
  }
}