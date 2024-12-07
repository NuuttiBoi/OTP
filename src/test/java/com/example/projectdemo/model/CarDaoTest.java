package com.example.projectdemo.model;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Testing CarDao class with mockito.
 */
class CarDaoTest {
  private CarDao carDao;
  @Mock
  private Connection mockConnection;
  @Mock
  private PreparedStatement mockPreparedStatement;
  @Mock
  private ResultSet mockResultSet;

  @BeforeEach
  void setUp() {
    Locale locale = new Locale("en","US");
    LanguageManager.setLocale(locale);
    carDao = new CarDao();
    mockConnection = mock(Connection.class);
    mockPreparedStatement = mock(PreparedStatement.class);
    mockResultSet = mock(ResultSet.class);
  }

  /**
   * Since in setUp the locale is set to be en_US, the currency should be USD.
   */
  @Test
  void setCurrency() {
    SessionManager.setCurrency("$");
    assertEquals("$",SessionManager.getCurrency());
  }

  @Test
  void testGetList() throws SQLException {
    when(mockConnection.createStatement()).thenReturn(mock(Statement.class));
    when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

    when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    when(mockResultSet.getString("car_id")).thenReturn("1");
    when(mockResultSet.getString("Make")).thenReturn("Toyota");
    when(mockResultSet.getString("Model")).thenReturn("Corolla");
    when(mockResultSet.getInt("Year")).thenReturn(2020);
    when(mockResultSet.getDouble("Price")).thenReturn(15000.0);

    List<Car> carList = carDao.getList();

    assertNotNull(carList);
    assertFalse(carList.isEmpty());
    assertEquals("Toyota", carList.get(0).getMake());
    assertEquals(2024, carList.get(0).getYear());
  }


  /*
  @Test
  void getCarsByLocation() throws SQLException {
    when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

    when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    when(mockResultSet.getString("car_id")).thenReturn("1");
    when(mockResultSet.getString("Make")).thenReturn("Toyota");
    when(mockResultSet.getString("Model")).thenReturn("Corolla");
    when(mockResultSet.getInt("Year")).thenReturn(2020);
    when(mockResultSet.getDouble("Price")).thenReturn(15000.0);

    List<Car> carsByLocation = carDao.getCarsByLocation("123", ResourceBundle.getBundle("Messages"));

    assertNotNull(carsByLocation);
    assertEquals(1, carsByLocation.size());
    assertEquals("Toyota", carsByLocation.get(0).getMake());
  }

   */




}