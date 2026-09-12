package dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.service;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models.Order;
import java.util.UUID;

public class ShippingService {

    public String createShipment(Order order) {

        String shipmentId =
                "shipment-" + UUID.randomUUID();

        System.out.println(
                "ShippingService: Shipment created for "
                        + order.getOrderId()
                        + " -> "
                        + shipmentId
        );

        return shipmentId;
    }
}
