package com.example.orderingsystem.service;

import com.example.orderingsystem.dto.CreateOrderRequest;
import com.example.orderingsystem.entity.CustomerOrder;
import com.example.orderingsystem.entity.MenuItem;
import com.example.orderingsystem.repository.CustomerOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final CustomerOrderRepository customerOrderRepository;
    private final MenuService menuService;

    public OrderService(CustomerOrderRepository customerOrderRepository, MenuService menuService) {
        this.customerOrderRepository = customerOrderRepository;
        this.menuService = menuService;
    }

    public CustomerOrder createOrder(CreateOrderRequest request) {
        MenuItem item = menuService.getMenuItem(request.menuItemId());

        CustomerOrder order = new CustomerOrder();
        order.setMenuItem(item);
        order.setQuantity(request.quantity());
        order.setStatus(CustomerOrder.OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        order.setCustomerName(request.customerName());
        return customerOrderRepository.save(order);
    }

    public List<CustomerOrder> findAllOrders() {
        return customerOrderRepository.findAll();
    }

    public List<CustomerOrder> findReceivedOrders() {
        return customerOrderRepository.findByStatus(CustomerOrder.OrderStatus.RECEIVED);
    }

    public CustomerOrder markAsReceived(Long orderId) {
        CustomerOrder order = getOrder(orderId);
        order.setStatus(CustomerOrder.OrderStatus.RECEIVED);
        return customerOrderRepository.save(order);
    }

    public CustomerOrder markAsServed(Long orderId) {
        CustomerOrder order = getOrder(orderId);
        order.setStatus(CustomerOrder.OrderStatus.SERVED);
        return customerOrderRepository.save(order);
    }

    private CustomerOrder getOrder(Long orderId) {
        return customerOrderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("未找到订单，ID=" + orderId));
    }
}
