package dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.stripe.models;

public class StripeRefund {

    private final String refundId;
    private final boolean successful;

    public StripeRefund(String refundId, boolean successful) {
        this.refundId = refundId;
        this.successful = successful;
    }

    public String getRefundId() {
        return refundId;
    }

    public boolean isSuccessful() {
        return successful;
    }
}
