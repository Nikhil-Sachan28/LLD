package dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.adapter;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.PaymentGateway;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.enums.PaymentStatus;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.exceptions.MissingDataException;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.models.PaymentRequest;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.models.PaymentResult;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.razorpay.ChargeStatus;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.razorpay.RazorpayClient;

import java.util.Optional;
import java.util.Scanner;

public class RazorpayAdapter implements PaymentGateway, PaymentInputCollector {
    private final RazorpayClient client;

    public RazorpayAdapter(RazorpayClient client){
        this.client = client;
    }

    @Override
    public PaymentResult pay(PaymentRequest request) throws MissingDataException {
        Optional<String> customerId = request.getCustomerId();
        double amount = request.getAmount();
        Optional<String> currency = request.getCurrency();
        if(customerId.isEmpty() || currency.isEmpty()){
            throw new MissingDataException("Either of customer ID or currency is missing");
        }
        int amountInPaise = toPaiseOrThrow(amount);

        String result = client.createOrder(customerId.get(), amountInPaise, currency.get());
        return new PaymentResult(result);
    }

    @Override
    public PaymentStatus getPaymentStatus(String transactionId) {
        ChargeStatus result = client.fetchOrderStatus(transactionId);
        return switch (result) {
            case CAPTURED -> PaymentStatus.SUCCESS;
            case CREATED -> PaymentStatus.PENDING;
            case FAILED -> PaymentStatus.FAILED;
        };
    }

    @Override
    public PaymentStatus refund(String transactionId, double amount) {
        int amountInPaise = toPaiseOrThrow(amount);
        // Fixed: boolean success from the provider was previously discarded.
        boolean success = client.initiateRefund(transactionId, amountInPaise);
        return success ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;
    }

    @Override
    public void collect(Scanner sc, PaymentRequest pr) {
        System.out.println("Enter customer ID");
        pr.setCustomerId(Optional.of(sc.nextLine()));
        System.out.println("Enter Currency");
        pr.setCurrency(Optional.of(sc.nextLine()));
    }

    // Fixed: (int)(amount * 100) could silently overflow for large amounts
    // since RazorpayClient's third-party signature is fixed to int paise.
    private int toPaiseOrThrow(double amount) {
        long paise = Math.round(amount * 100);
        if (paise > Integer.MAX_VALUE || paise < 0) {
            throw new IllegalArgumentException("Amount out of range for Razorpay's paise representation: " + amount);
        }
        return (int) paise;
    }
}
