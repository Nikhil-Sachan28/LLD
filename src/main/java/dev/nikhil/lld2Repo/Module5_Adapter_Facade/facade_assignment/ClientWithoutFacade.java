package dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models.FraudResult;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models.Order;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models.PaymentResult;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.service.*;

public class ClientWithoutFacade {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        InventoryService inventoryService = new InventoryService();
        FraudDetectionService fraudDetectionService = new FraudDetectionService();
        PaymentService paymentService = new PaymentService();
        ShippingService shippingService = new ShippingService();
        NotificationService notificationService = new NotificationService();
        String orderId = "order-1";

        Order order = orderService.getOrder(orderId);

        if (!inventoryService.checkAvailability(order)) {
            return;
        }

        inventoryService.reserve(order);

        FraudResult fraud = fraudDetectionService.check(order);

        if (fraud.isBlocked()) {
            inventoryService.release(order);
            return;
        }

        PaymentResult payment =
                paymentService.charge(
                        order.getCustomerId(),
                        order.getTotalAmount()
                );

        if (!payment.isSuccessful()) {
            inventoryService.release(order);
            return;
        }

        String shipment =
                shippingService.createShipment(order);

        orderService.markConfirmed(orderId);

        notificationService.sendOrderConfirmation(
                order.getCustomerId(),
                orderId
        );
    }
}
