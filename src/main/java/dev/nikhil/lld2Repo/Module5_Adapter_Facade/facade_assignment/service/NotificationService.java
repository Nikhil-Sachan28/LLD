package dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.service;

public class NotificationService {

    public void sendOrderConfirmation(
            String customerId,
            String orderId
    ) {

        System.out.println(
                "NotificationService: Confirmation sent to "
                        + customerId
                        + " for order "
                        + orderId
        );
    }
}
