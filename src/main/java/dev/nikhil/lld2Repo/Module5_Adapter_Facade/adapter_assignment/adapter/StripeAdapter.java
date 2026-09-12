package dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.adapter;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.PaymentGateway;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.enums.PaymentStatus;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.exceptions.MissingDataException;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.models.PaymentRequest;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.models.PaymentResult;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.stripe.StripeChargeStatus;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.stripe.StripeClient;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.stripe.models.StripeCharge;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.stripe.models.StripeRefund;

import java.util.Optional;
import java.util.Scanner;

public class StripeAdapter implements PaymentGateway, PaymentInputCollector {
    private final StripeClient stripeClient;

    public StripeAdapter(StripeClient stripeClient){
        this.stripeClient = stripeClient;
    }

    @Override
    public PaymentResult pay(PaymentRequest request) throws MissingDataException {
        double amount = request.getAmount();
        long amountInSmallestUnit = (long) (amount * 100);
        Optional<String> currency = request.getCurrency();
        Optional<String> email = request.getCustomerEmail();
        if(currency.isEmpty() || email.isEmpty()){
            throw new MissingDataException("Currency or email is missing");
        }
        StripeCharge result = stripeClient.createCharge(amountInSmallestUnit, currency.get(), email.get());
        return new PaymentResult(result.getChargeId());
    }

    @Override
    public PaymentStatus getPaymentStatus(String transactionId) {
        StripeChargeStatus status = stripeClient.retrieveCharge(transactionId);
        return switch (status) {
            case SUCCEEDED -> PaymentStatus.SUCCESS;
            case PENDING -> PaymentStatus.PENDING;
            case FAILED -> PaymentStatus.FAILED;
        };
    }

    @Override
    public PaymentStatus refund(String transactionId, double amount) {
        long amountInSmallestUnit = (long) (amount * 100);
        // Fixed: StripeRefund.isSuccessful() was previously ignored entirely.
        StripeRefund refund = stripeClient.createRefund(transactionId, amountInSmallestUnit);
        return refund.isSuccessful() ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;
    }

    @Override
    public void collect(Scanner sc, PaymentRequest pr) {
        System.out.println("Enter Currency");
        pr.setCurrency(Optional.of(sc.nextLine()));
        System.out.println("Enter customer Email");
        pr.setCustomerEmail(Optional.of(sc.nextLine()));
    }
}
