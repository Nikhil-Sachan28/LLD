package dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.facade.CheckoutFacade;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models.CheckoutResult;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.service.*;

public class ClientWithFacade {

    public static void main(String[] args) {

        CheckoutFacade checkoutFacade = new CheckoutFacade();

        String orderId = "order-1";

        CheckoutResult result = checkoutFacade.checkout(orderId);

        System.out.println(
                "Checkout status: " + result.getStatus()
        );

        System.out.println(
                "Order ID: " + result.getOrderId()
        );

        System.out.println(
                "Transaction ID: " + result.getTransactionId()
        );

        System.out.println(
                "Shipment ID: " + result.getShipmentId()
        );
    }
}