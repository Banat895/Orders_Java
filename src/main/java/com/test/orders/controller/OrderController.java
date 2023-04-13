package com.test.orders.controller;

import com.test.orders.dto.OrderDto;
import com.test.orders.entities.Order;
import com.test.orders.exception.OrderNotFoundException;
import com.test.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody @Valid OrderDto order) {
        return new ResponseEntity<>(orderService.create(order), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> get(@PathVariable("id") Long id) throws OrderNotFoundException {
        return new ResponseEntity<>(orderService.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> get() {
        return new ResponseEntity<>(orderService.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> put(@PathVariable("id") Long id, @RequestBody @Valid OrderDto orderDto) throws OrderNotFoundException {
        return new ResponseEntity<>(orderService.put(id,orderDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws OrderNotFoundException {
        orderService.delete(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
