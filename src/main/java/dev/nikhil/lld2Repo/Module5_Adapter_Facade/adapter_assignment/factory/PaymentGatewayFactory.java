package dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.factory;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.PaymentGateway;

import java.util.HashMap;
import java.util.Map;

public class PaymentGatewayFactory {
    private static final Map<String, PaymentGateway> registry = new HashMap<>();

    public static void registerGateway(String type, PaymentGateway gateway){
        registry.put(normalize(type), gateway);
    }

    public static PaymentGateway getGateway(String type){
        PaymentGateway gateway = registry.get(normalize(type));
        if (gateway == null) {
            throw new IllegalArgumentException("Enter a valid type");
        }
        return gateway;
    }

    // Fixed: lookups were case-sensitive ("RAZORPAY" registered, "razorpay"
    // typed by the user would fail) even though nothing about a gateway type
    // is case-meaningful.
    private static String normalize(String type) {
        return type == null ? null : type.trim().toUpperCase();
    }
}
