package dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.facade;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models.CheckoutResult;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models.FraudResult;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models.Order;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models.PaymentResult;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.service.*;

public class CheckoutFacade {

    private final OrderService orderService;
    private final InventoryService inventoryService;
    private final FraudDetectionService fraudDetectionService;
    private final PaymentService paymentService;
    private final ShippingService shippingService;
    private final NotificationService notificationService;

    public CheckoutFacade() {
        this.orderService = new OrderService();
        this.inventoryService = new InventoryService();
        this.fraudDetectionService = new FraudDetectionService();
        this.paymentService = new PaymentService();
        this.shippingService = new ShippingService();
        this.notificationService = new NotificationService();
    }

    public CheckoutResult checkout(String orderId) {

        // 1. Fetch order
        Order order = orderService.getOrder(orderId);

        // 2. Check inventory
        if (!inventoryService.checkAvailability(order)) {
            return new CheckoutResult(
                    CheckoutResult.Status.OUT_OF_STOCK,
                    orderId,
                    null,
                    null
            );
        }

        // 3. Reserve inventory
        inventoryService.reserve(order);

        // 4. Fraud check
        FraudResult fraudResult = fraudDetectionService.check(order);

        if (fraudResult.isBlocked()) {

            inventoryService.release(order);

            return new CheckoutResult(
                    CheckoutResult.Status.FRAUD_DETECTED,
                    orderId,
                    null,
                    null
            );
        }

        // 5. Payment
        PaymentResult paymentResult =
                paymentService.charge(
                        order.getCustomerId(),
                        order.getTotalAmount()
                );

        if (!paymentResult.isSuccessful()) {

            inventoryService.release(order);

            return new CheckoutResult(
                    CheckoutResult.Status.PAYMENT_FAILED,
                    orderId,
                    null,
                    null
            );
        }

        // 6. Create shipment
        String shipmentId = shippingService.createShipment(order);

        // 7. Confirm order
        orderService.markConfirmed(orderId);

        // 8. Notify customer
        notificationService.sendOrderConfirmation(
                order.getCustomerId(),
                orderId
        );

        // 9. Return result
        return new CheckoutResult(
                CheckoutResult.Status.SUCCESS,
                orderId,
                paymentResult.getTransactionId(),
                shipmentId
        );
    }
}