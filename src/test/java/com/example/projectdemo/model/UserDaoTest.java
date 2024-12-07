package com.example.projectdemo.model;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit testing UserDao class with mockito.
 */
public class UserDaoTest {

  private UserDao userDao;
  private Connection mockConnection;
  private PreparedStatement mockPreparedStatement;
  private ResultSet mockResultSet;

  @BeforeEach
  public void setUp() {
    userDao = new UserDao();
    mockConnection = mock(Connection.class);
    mockPreparedStatement = mock(PreparedStatement.class);
    mockResultSet = mock(ResultSet.class);
  }

  @Test
  public void testValidateInvalidUser() throws SQLException {
    String email = "nonexistent@example.com";
    String password = "wrongpassword";

    when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

    when(mockResultSet.next()).thenReturn(false);

    User user = userDao.validate(email, password);

    assertNull(user);
  }
}
