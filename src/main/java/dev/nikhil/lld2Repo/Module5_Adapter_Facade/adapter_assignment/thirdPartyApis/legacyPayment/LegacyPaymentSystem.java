package dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.thirdPartyApis.legacyPayment;

public class LegacyPaymentSystem {

    public int makePayment(
            String accountNumber,
            double amount
    ) {
        // returns legacy transaction number
        return 1001;
    }

    public String checkTransaction(int transactionNumber) {
        // returns "SUCCESS", "FAILED", "PENDING"
        return "SUCCESS";
    }

    public boolean reverseTransaction(
            int transactionNumber,
            double amount
    ) {
        // existing implementation
        return true;
    }
}
