package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.paymentService;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.exception.InvalidAmount;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.exception.InvalidDetails;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.payment.Payment;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.payment.UpiPayment;

public class UpiPaymentService extends PaymentService{
    private final String upiId;
    private static double limit = 100000;
    private static int counter = 0;
    private final String utr;

    public UpiPaymentService(String upiId, double amount) {
        super(amount);
        this.upiId = upiId;
        this.utr = generateUtr();
    }

    @Override
    public void validate() {
        if(getAmount() > limit){
            throw new InvalidAmount("Amount can't be greater than " + limit);
        }
        if(!isValidUpiId(upiId)){
            throw new InvalidDetails("Enter a Valid UPI ID");
        }
    }

    @Override
    public Payment createPayment() {
        return new UpiPayment(getAmount(), upiId);
    }

    @Override
    public void generateReceipt() {
        System.out.println("UPI ID:" + upiId);
        System.out.println("UTR:" + utr);
        System.out.println("Amount:" + getAmount());
    }

    public static boolean isValidUpiId(String upiId) {
        return upiId.matches("^[a-zA-Z0-9._-]+@[a-zA-Z]+$");
    }

    public static String generateUtr(){
        synchronized (UpiPaymentService.class){
            return "txn-" + ++counter;
        }
    }


}
