package dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.stripe.models;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.stripe.StripeChargeStatus;

public class StripeCharge {

    private final String chargeId;
    private final StripeChargeStatus status;

    public StripeCharge(String chargeId, StripeChargeStatus status) {
        this.chargeId = chargeId;
        this.status = status;
    }

    public String getChargeId() {
        return chargeId;
    }

    public StripeChargeStatus getStatus() {
        return status;
    }
}
