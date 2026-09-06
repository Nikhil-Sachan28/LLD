package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.factory;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.exception.InvalidDetails;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.paymentService.CreditCardPaymentService;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.paymentService.PaymentService;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.paymentService.UpiPaymentService;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.PaymentType;

import java.util.Scanner;


public class PaymentFactory {

    public static PaymentService getPaymentService(PaymentType paymentType, double amount, Scanner sc){
        if(paymentType.equals(PaymentType.UPI)){
            System.out.println("Enter UPI Id");
            String upiId = sc.nextLine();
            return new UpiPaymentService(upiId, amount);
        }else if(paymentType.equals(PaymentType.CREDIT_CARD)){
            System.out.println("Enter Card Number");
            String cardNumber = sc.nextLine();
            System.out.println("Enter CVV");
            String cvv = sc.nextLine();
            System.out.println("Enter Expiry date");
            String expiry = sc.nextLine();
            return new CreditCardPaymentService(cardNumber, cvv, expiry, amount);
        }
        throw new InvalidDetails("You can choose either Upi(1) or Credit Card(1) only");

    }
}
