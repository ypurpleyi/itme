package com.example.orderingsystem.controller;

import com.example.orderingsystem.dto.CreateOrderRequest;
import com.example.orderingsystem.entity.CustomerOrder;
import com.example.orderingsystem.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public CustomerOrder createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping
    public List<CustomerOrder> listOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/received")
    public List<CustomerOrder> listReceivedOrders() {
        return orderService.findReceivedOrders();
    }

    @PatchMapping("/{orderId}/receive")
    public CustomerOrder receiveOrder(@PathVariable Long orderId) {
        return orderService.markAsReceived(orderId);
    }

    @PatchMapping("/{orderId}/serve")
    public CustomerOrder serveOrder(@PathVariable Long orderId) {
        return orderService.markAsServed(orderId);
    }
}
