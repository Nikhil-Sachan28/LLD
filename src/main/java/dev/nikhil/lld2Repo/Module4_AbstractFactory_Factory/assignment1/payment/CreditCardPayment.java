package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.payment;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.exception.TransactionFailed;

public class CreditCardPayment extends Payment{
    private final String cardNumber;
    private final String cvv;
    private final String expiry;
    private final double amount;

    public CreditCardPayment(String cardNumber, String cvv, String expiry, double amount) {
        this.amount = amount;
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.cvv = cvv;
    }

    @Override
    public void processTransaction() {
        try{
            System.out.println("transaction in Process..");
            Thread.sleep(1000);
            System.out.println("transaction Completed");
        }catch (InterruptedException ex){
            throw new TransactionFailed("transaction failed");
        }
    }
}
