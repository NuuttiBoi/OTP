package com.example.projectdemo.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LocationDAOTest {
    private LocationDAO locationDAO;

    @Mock
    private ConnectDb mockConnectDb;

    @Mock
    private Connection mockConnection;

    @Mock
    private Statement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);

        // Initialize the DAO with a mock ConnectDb instance
        locationDAO = new LocationDAO("Test Location");

        // Mock the connection, statement, and result set
        when(mockConnectDb.connect()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
    }

    @Test
    void testGetLocationList_EmptyResultSet() throws SQLException {
        // Mock empty result set
        when(mockResultSet.next()).thenReturn(false);

        List<Location> locationList = locationDAO.getLocationList();

        // Assert that the list is empty
        assertTrue(locationList.isEmpty(), "Location list should be empty when result set has no data");
    }

    @Test
    void testGetLocationList_NonEmptyResultSet() throws SQLException {
        // Mock result set with data
        when(mockResultSet.next()).thenReturn(true, false); // First call returns true, second returns false
        when(mockResultSet.getString("LocationID")).thenReturn("1");
        when(mockResultSet.getString("Location")).thenReturn("Helsinki");
        when(mockResultSet.getString("Address")).thenReturn("Example Address");

        List<Location> locationList = locationDAO.getLocationList();

        // Verify the size of the returned list
        assertEquals(1, locationList.size(), "Location list should contain one element");

        // Verify the content of the first element
        Location location = locationList.get(0);
        assertEquals("Helsinki", location.getName(), "Location name should match");
        assertEquals("Example Address", location.getAddress(), "Address should match");
    }

    @Test
    void testGetLocationList_SQLException() throws SQLException {
        // Simulate an SQL exception when creating a statement
        when(mockConnection.createStatement()).thenThrow(new SQLException("Database error"));

        // Assert that a RuntimeException is thrown
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            locationDAO.getLocationList();
        });

        assertEquals("java.sql.SQLException: Database error", exception.getCause().toString());
    }

}