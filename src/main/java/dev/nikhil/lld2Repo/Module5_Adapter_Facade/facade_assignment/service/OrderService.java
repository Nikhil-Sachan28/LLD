package dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.service;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models.Order;


import java.util.HashMap;
import java.util.Map;

public class OrderService {

    private final Map<String, Order> orders = new HashMap<>();

    public OrderService() {
        orders.put(
                "order-1",
                new Order("order-1", "customer-1", 1000)
        );

        orders.put(
                "order-2",
                new Order("order-2", "customer-2", 5000)
        );

        orders.put(
                "order-3",
                new Order("order-3", "customer-3", 2000)
        );
    }

    public Order getOrder(String orderId) {
        System.out.println("OrderService: Fetching order " + orderId);

        Order order = orders.get(orderId);

        if (order == null) {
            throw new IllegalArgumentException(
                    "Order not found: " + orderId
            );
        }

        return order;
    }

    public void markConfirmed(String orderId) {
        System.out.println(
                "OrderService: Order " + orderId + " confirmed"
        );
    }

    public void cancel(String orderId) {
        System.out.println(
                "OrderService: Order " + orderId + " cancelled"
        );
    }
}