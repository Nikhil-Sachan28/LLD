package dev.nikhil.lld2Repo.Module5_Adapter_Facade.facade_assignment.models;

public class CheckoutResult {

    public enum Status {
        SUCCESS,
        OUT_OF_STOCK,
        FRAUD_DETECTED,
        PAYMENT_FAILED
    }

    private final Status status;
    private final String orderId;
    private final String transactionId;
    private final String shipmentId;

    public CheckoutResult(
            Status status,
            String orderId,
            String transactionId,
            String shipmentId
    ) {
        this.status = status;
        this.orderId = orderId;
        this.transactionId = transactionId;
        this.shipmentId = shipmentId;
    }

    public Status getStatus() {
        return status;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public boolean isSuccessful() {
        return status == Status.SUCCESS;
    }
}
