package com.test.orders.service;

import com.test.orders.dto.OrderDto;
import com.test.orders.entities.Order;
import com.test.orders.exception.OrderNotFoundException;

import java.util.List;

public interface OrderService {

    Order create(OrderDto order);

    List<OrderDto> get();

    OrderDto get(Long id) throws OrderNotFoundException;

    void delete(Long id) throws OrderNotFoundException;

    Order put(Long id, OrderDto orderDto) throws OrderNotFoundException;
}
