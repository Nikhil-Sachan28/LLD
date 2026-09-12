package dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models;

public class PaymentResult {

    private final boolean successful;
    private final String transactionId;

    public PaymentResult(boolean successful, String transactionId) {
        this.successful = successful;
        this.transactionId = transactionId;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getTransactionId() {
        return transactionId;
    }
}