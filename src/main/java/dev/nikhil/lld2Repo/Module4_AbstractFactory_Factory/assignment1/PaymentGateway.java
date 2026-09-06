package dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1;

import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.exception.InvalidAmount;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.exception.InvalidDetails;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.exception.TransactionFailed;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.paymentService.PaymentService;
import dev.nikhil.lld2Repo.Module4_AbstractFactory_Factory.assignment1.factory.PaymentFactory;

import java.util.Scanner;

public class PaymentGateway {
    public static void main(String[] args) {
        boolean continueTransaction = true;
        Scanner sc = new Scanner(System.in);

        while (continueTransaction) {
            try {
                System.out.println("Enter amount you want to transfer");
                double amount = sc.nextDouble();
                sc.nextLine();

                System.out.println("select Payment method");
                System.out.println("0- for UPI");
                System.out.println("1- for Credit card");
                int ordinal = sc.nextInt();
                sc.nextLine();

                if (ordinal < 0 || ordinal >= PaymentType.values().length) {
                    System.out.println("invalid Input");
                } else {
                    PaymentType paymentType = PaymentType.values()[ordinal];
                    System.out.println("Choosen Value is " + paymentType);

                    PaymentService paymentService = PaymentFactory.getPaymentService(paymentType, amount, sc);
                    paymentService.executePayment();
                }

            } catch (InvalidAmount | InvalidDetails ex) {
                System.out.println("Transaction rejected: " + ex.getMessage());
            } catch (TransactionFailed ex) {
                System.out.println("Transaction failed: " + ex.getMessage());
            } catch (IllegalArgumentException ex) {
                System.out.println("Unsupported option: " + ex.getMessage());
            }

            System.out.println("wanna make another transaction?\n 0- NO\n 1-yes");
            int input = sc.nextInt();
            sc.nextLine();
            continueTransaction = input != 0;
        }
    }
}
