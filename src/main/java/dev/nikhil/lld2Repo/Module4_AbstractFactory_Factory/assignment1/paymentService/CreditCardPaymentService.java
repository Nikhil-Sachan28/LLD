package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.paymentService;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.exception.InvalidDetails;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.payment.CreditCardPayment;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.payment.Payment;

public class CreditCardPaymentService extends PaymentService{
    private final String cardNumber;
    private final String cvv;
    private final String expiry;

    public CreditCardPaymentService(String cardNumber, String cvv, String expiry, double amount) {
        super(amount);
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.cvv = cvv;
    }

    @Override
    public void validate() {
        if(!validateCardDetails()){
            throw new InvalidDetails("Card details are wrong");
        }
        if(getAmount() > 50000){
            if(!otpVerified()){
                throw new InvalidDetails("invalid OTP");
            }
        }
    }

    boolean otpVerified(){
        return true;
    }

    @Override
    public Payment createPayment() {
        return new CreditCardPayment(cardNumber, cvv, expiry, getAmount());
    }

    @Override
    public void generateReceipt() {
        System.out.println("Card Number:- **** **** ****" + cardNumber.substring(12,16));
        System.out.println("amount: " + getAmount());
    }

    private boolean validateCardDetails(){
        return true;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public String getExpiry() {
        return expiry;
    }
}
