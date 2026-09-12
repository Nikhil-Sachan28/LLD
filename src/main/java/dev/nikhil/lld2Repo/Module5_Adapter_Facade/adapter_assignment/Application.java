package dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.adapter.LegacyPaymentAdapter;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.adapter.PaymentInputCollector;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.adapter.RazorpayAdapter;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.adapter.StripeAdapter;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.exceptions.MissingDataException;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.factory.PaymentGatewayFactory;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.models.PaymentRequest;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.models.PaymentResult;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.legacyPayment.LegacyPaymentSystem;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.razorpay.RazorpayClient;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.stripe.StripeClient;

import java.util.Scanner;

public class Application {
    public static void main(String[] args){
        PaymentGatewayFactory.registerGateway("RAZORPAY", new RazorpayAdapter(new RazorpayClient()));
        PaymentGatewayFactory.registerGateway("STRIPE", new StripeAdapter(new StripeClient()));
        PaymentGatewayFactory.registerGateway("LEGACY", new LegacyPaymentAdapter(new LegacyPaymentSystem()));

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Amount");
        PaymentRequest pr = new PaymentRequest(sc.nextDouble());
        sc.nextLine();

        System.out.println("Choose method \nRAZORPAY \nSTRIPE \nLEGACY");
        String type = sc.nextLine();
        PaymentGateway pg = PaymentGatewayFactory.getGateway(type);

        // Fixed: takeInput no longer lives on PaymentGateway. Only adapters
        // that need console input implement PaymentInputCollector, so a
        // future gateway (e.g. one used purely from a REST layer) doesn't
        // have to implement input collection at all.
        if (pg instanceof PaymentInputCollector collector) {
            collector.collect(sc, pr);
        }

        PaymentResult result;
        try {
            result = pg.pay(pr);
        } catch (MissingDataException ex) {
            // Fixed: previously execution fell through to
            // pg.getPaymentStatus(result.trnId()) with result == null,
            // throwing a NullPointerException on every failed payment.
            System.out.println("Payment failed: " + ex.getMessage());
            return;
        }

        System.out.println("Transaction ID: " + result.trnId());
        System.out.println("Status: " + pg.getPaymentStatus(result.trnId()));
    }
}
