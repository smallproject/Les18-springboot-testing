package com.example.les18.service;

import com.example.les18.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.les18.model.Order;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository repository;

    @InjectMocks // dit geeft aan dat we de mocks met @Mock hierin geladen kunnen worden.
    OrderService service;

    @Test
    @DisplayName("Get Order")
    void getOrder() {
        // Arrange
        var name = "Television";
        var price = 699.0;
        var quantity = 10;

        Order order = new Order(name, price, quantity);
        order.setOrderid(123);
        when(repository.findById(anyInt())).thenReturn(Optional.of(order));
        // Act
        var orderDto = service.getOrder(123);

        // Assert
        assertEquals(quantity, orderDto.quantity);
        assertEquals(name, orderDto.productname);
        assertEquals(price, orderDto.unitprice);
    }
}