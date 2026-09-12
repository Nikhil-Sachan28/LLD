package dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.service;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models.PaymentResult;

import java.util.UUID;

public class PaymentService {

    public PaymentResult charge(
            String customerId,
            double amount
    ) {

        System.out.println(
                "PaymentService: Charging customer "
                        + customerId
                        + " amount ₹"
                        + amount
        );

        if (amount > 3000) {

            System.out.println(
                    "PaymentService: Payment failed"
            );

            return new PaymentResult(false, null);
        }

        String transactionId =
                "txn-" + UUID.randomUUID();

        System.out.println(
                "PaymentService: Payment successful. Transaction: "
                        + transactionId
        );

        return new PaymentResult(
                true,
                transactionId
        );
    }

    public void refund(String transactionId) {

        System.out.println(
                "PaymentService: Refunded transaction "
                        + transactionId
        );
    }
}