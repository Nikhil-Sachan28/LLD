package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.serviceLoader;

public class RazorpayGateway implements PaymentGateway {

    @Override
    public void pay(int amount) {
        System.out.println(
                "Paying ₹" + amount + " using Razorpay"
        );
    }
}
