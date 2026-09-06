package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.serviceLoader;

import java.util.ServiceLoader;
/**
    ServiceLoader searches for implementations of an interface that have been registered as
    Java services, and gives those implementations to you.
**/
public class Main {
    public static void main(String[] args) {
        ServiceLoader<PaymentGateway> loader = ServiceLoader.load(PaymentGateway.class);

        for (PaymentGateway gateway : loader) {
            gateway.pay(100);
        }
    }
}

