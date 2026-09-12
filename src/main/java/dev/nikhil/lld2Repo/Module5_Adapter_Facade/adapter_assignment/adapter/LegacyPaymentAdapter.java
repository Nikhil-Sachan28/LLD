package dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.adapter;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.PaymentGateway;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.enums.PaymentStatus;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.exceptions.MissingDataException;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.models.PaymentRequest;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.models.PaymentResult;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.legacyPayment.LegacyPaymentSystem;

import java.util.Optional;
import java.util.Scanner;

public class LegacyPaymentAdapter implements PaymentGateway, PaymentInputCollector {
    private final LegacyPaymentSystem paymentSystem;

    public LegacyPaymentAdapter(LegacyPaymentSystem paymentSystem){
        this.paymentSystem = paymentSystem;
    }

    @Override
    public PaymentResult pay(PaymentRequest request) throws MissingDataException {
        Optional<String> accountNumber = request.getAccountNumber();
        double amount = request.getAmount();
        if(accountNumber.isEmpty()){
            throw new MissingDataException("Account Number Missing");
        }
        int result = paymentSystem.makePayment(accountNumber.get(), amount);
        return new PaymentResult(String.valueOf(result));
    }

    @Override
    public PaymentStatus getPaymentStatus(String transactionId) {
        String result = paymentSystem.checkTransaction(Integer.parseInt(transactionId));
        return switch (result) {
            case "SUCCESS" -> PaymentStatus.SUCCESS;
            case "PENDING" -> PaymentStatus.PENDING;
            default -> PaymentStatus.FAILED;
        };
    }

    @Override
    public PaymentStatus refund(String transactionId, double amount) {
        // Fixed: the boolean success signal from the provider was previously
        // discarded entirely (method returned void).
        boolean success = paymentSystem.reverseTransaction(Integer.parseInt(transactionId), amount);
        return success ? PaymentStatus.SUCCESS : PaymentStatus.FAILED;
    }

    @Override
    public void collect(Scanner sc, PaymentRequest pr) {
        System.out.println("Enter account number");
        pr.setAccountNumber(Optional.of(sc.nextLine()));
    }
}
