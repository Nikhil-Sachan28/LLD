package dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.stripe;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.stripe.models.StripeCharge;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.stripe.models.StripeRefund;

public class StripeClient {

    public StripeCharge createCharge(
            long amount,
            String currency,
            String customerEmail
    ) {
        System.out.println(
                "Stripe: Creating charge for " +
                        amount + " " + currency
        );

        return new StripeCharge(
                "stripe_charge_123",
                StripeChargeStatus.SUCCEEDED
        );
    }

    public StripeChargeStatus retrieveCharge(String chargeId) {
        System.out.println(
                "Stripe: Fetching status for " + chargeId
        );

        return StripeChargeStatus.SUCCEEDED;
    }

    public StripeRefund createRefund(
            String chargeId,
            long amount
    ) {
        System.out.println(
                "Stripe: Refunding " +
                        amount + " for " + chargeId
        );

        return new StripeRefund(
                "stripe_refund_123",
                true
        );
    }
}
