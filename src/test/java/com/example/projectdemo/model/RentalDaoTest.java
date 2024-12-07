package com.example.projectdemo.model;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RentalDaoTest {

  private RentalDao rentalDao;
  private Connection mockConnection;
  private PreparedStatement mockPreparedStatement;
  private ResultSet mockResultSet;

  @BeforeEach
  public void setUp() {
    rentalDao = new RentalDao();
    mockConnection = mock(Connection.class);
    mockPreparedStatement = mock(PreparedStatement.class);
    mockResultSet = mock(ResultSet.class);
  }



  // Test for getRentals with no results
  @Test
  public void testGetRentalsNoResults() throws SQLException {
    int registrationId = 1;

    when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    when(mockResultSet.next()).thenReturn(false); //

    List<Rental> rentals = rentalDao.getRentals(registrationId);

    assertNotNull(rentals);
    assertEquals(0, rentals.size());
  }
}
