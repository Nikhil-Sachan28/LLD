package dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.razorpay;

public class RazorpayClient {

    public String createOrder(
            String customerId,
            int amountInPaise,
            String currency
    ) {
        System.out.println(
                "Razorpay: Creating order for " +
                        amountInPaise + " " + currency
        );

        return "rzp_order_123";
    }

    public ChargeStatus fetchOrderStatus(String orderId) {
        System.out.println(
                "Razorpay: Fetching status for " + orderId
        );

        return ChargeStatus.CAPTURED;
    }

    public boolean initiateRefund(
            String orderId,
            int amountInPaise
    ) {
        System.out.println(
                "Razorpay: Refunding " +
                        amountInPaise + " for " + orderId
        );

        return true;
    }
}
