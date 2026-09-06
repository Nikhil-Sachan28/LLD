package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.paymentService;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.payment.Payment;

public abstract class PaymentService {
    private final double amount;

    PaymentService(double amount){
        this.amount = amount;
    }

    public void executePayment(){
        validate();
        Payment payment = createPayment();
        payment.processTransaction();
        generateReceipt();
    }

    public abstract void validate();
    public abstract Payment createPayment();
    public abstract void generateReceipt();

    public double getAmount(){
        return amount;
    }
}
