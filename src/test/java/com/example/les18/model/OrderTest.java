package com.example.les18.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Should keep product name")
    void shouldKeepProductName() {
        // arrange
        Order o = new Order("Philips televisie", 599.99, 3);

        // act
        String str = o.getProductname();

        // assert
        assertEquals("Philips televisie", str);
    }
}