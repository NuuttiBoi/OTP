package com.example.projectdemo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserConstructorWithParameters() {
        User user = new User(1, "test@example.com", "admin", "regular", "rental123");

        assertEquals(1, user.getUserID());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("admin", user.getRole());
        assertEquals("regular", user.getType());
        assertEquals("rental123", user.getRentalID());
    }

    @Test
    void testUserDefaultConstructor() {
        User user = new User();

        assertEquals(0, user.getUserID());
        assertNull(user.getEmail());
        assertNull(user.getRole());
        assertNull(user.getType());
        assertNull(user.getRentalID());
    }

    @Test
    void testSetters() {
        User user = new User();

        user.setUserID(2);
        user.setEmail("user@example.com");
        user.setRole("user");
        user.setType("premium");
        user.setRentalID("rental456");

        assertEquals(2, user.getUserID());
        assertEquals("user@example.com", user.getEmail());
        assertEquals("user", user.getRole());
        assertEquals("premium", user.getType());
        assertEquals("rental456", user.getRentalID());
    }

    @Test
    void testToString() {
        User user = new User(1, "test@example.com", "admin", "regular", "rental123");
        String expectedString = "User{userID=1, email='test@example.com', role='admin', type='regular', rentalID='rental123'}";

        assertEquals(expectedString, user.toString());
    }
}