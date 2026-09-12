package dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.service;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models.FraudResult;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models.Order;


public class FraudDetectionService {

    public FraudResult check(Order order) {

        System.out.println(
                "FraudDetectionService: Checking order "
                        + order.getOrderId()
        );

        boolean blocked =
                order.getOrderId().equals("order-3");

        if (blocked) {
            System.out.println(
                    "FraudDetectionService: Order blocked"
            );
        } else {
            System.out.println(
                    "FraudDetectionService: Order passed"
            );
        }

        return new FraudResult(blocked);
    }
}