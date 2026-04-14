package com.example.orderingsystem.repository;

import com.example.orderingsystem.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    List<CustomerOrder> findByStatus(CustomerOrder.OrderStatus status);
}
