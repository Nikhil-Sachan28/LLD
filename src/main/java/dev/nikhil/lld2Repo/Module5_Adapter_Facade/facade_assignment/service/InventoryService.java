package dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.service;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models.Order;

import java.util.HashSet;
import java.util.Set;

public class InventoryService {

    private final Set<String> unavailableOrders = new HashSet<>();

    public InventoryService() {
        unavailableOrders.add("order-2");
    }

    public boolean checkAvailability(Order order) {

        System.out.println(
                "InventoryService: Checking availability"
        );

        return !unavailableOrders.contains(order.getOrderId());
    }

    public void reserve(Order order) {

        System.out.println(
                "InventoryService: Inventory reserved for "
                        + order.getOrderId()
        );
    }

    public void release(Order order) {

        System.out.println(
                "InventoryService: Inventory released for "
                        + order.getOrderId()
        );
    }
}