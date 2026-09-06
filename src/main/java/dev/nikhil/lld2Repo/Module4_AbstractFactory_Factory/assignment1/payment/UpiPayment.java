package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.payment;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.exception.TransactionFailed;

public class UpiPayment extends Payment{
    private final double amount;
    private final String upiId;

    public UpiPayment(double amount, String upiId){
        this.amount = amount;
        this.upiId = upiId;
    }

    @Override
    public void processTransaction() {
        try{
            System.out.println("transaction in Process..");
            Thread.sleep(1000);
            System.out.println("transaction Completed");
        }catch (InterruptedException ex){
            throw new TransactionFailed("trnsaction failed");
        }
    }
}
