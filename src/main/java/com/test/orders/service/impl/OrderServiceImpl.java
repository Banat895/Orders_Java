package com.test.orders.service.impl;

import com.test.orders.dto.OrderDto;
import com.test.orders.entities.Extra;
import com.test.orders.entities.Order;
import com.test.orders.entities.VegetableEntity;
import com.test.orders.enums.Vegetable;
import com.test.orders.exception.OrderNotFoundException;
import com.test.orders.repository.OrderRepository;
import com.test.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order create(OrderDto orderDto) {
        return orderRepository.save(getOrder(orderDto));
    }

    @Override
    public List<OrderDto> get() {
        return orderRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDto get(Long id) throws OrderNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));
        return convertToDTO(order);
    }

    @Override
    public void delete(Long id) throws OrderNotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));
        orderRepository.delete(order);
    }

    @Override
    public Order put(Long id, OrderDto orderDto) throws OrderNotFoundException {
        Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with id " + id));
        Order order = getOrder(orderDto);
        order.setId(id);
        return orderRepository.save(order);
    }

    private Order getOrder(OrderDto orderDto) {
        Order.OrderBuilder orderBuilder = Order.builder()
                .tortilla(orderDto.getTortilla())
                .protein(orderDto.getProtein())
                .salsa(orderDto.getSalsa());
        List<String> extras = orderDto.getExtras();
        if (extras != null) {
            orderBuilder.extras(extras.stream().map(s -> Extra.builder().extra(s).build()).collect(Collectors.toSet()));
        }
        List<Vegetable> vegetables = orderDto.getVegetables();
        if (vegetables != null) {
            orderBuilder.vegetables(vegetables.stream().map(vegetable -> VegetableEntity.builder().vegetable(vegetable).build()).collect(Collectors.toSet()));
        }
        return orderBuilder.build();
    }

    private OrderDto convertToDTO(Order order) {
        return OrderDto.builder()
                .salsa(order.getSalsa())
                .tortilla(order.getTortilla())
                .protein(order.getProtein())
                .vegetables(order.getVegetables().stream().map(VegetableEntity::getVegetable).collect(Collectors.toList()))
                .extras(order.getExtras().stream().map(Extra::getExtra).collect(Collectors.toList()))
                .build();
    }
}
