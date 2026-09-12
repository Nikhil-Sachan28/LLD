package dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.models;

import java.util.Optional;

public class PaymentRequest {
    private final double amount;

    // Fixed: these were never initialized, so getAccountNumber().isEmpty()
    // etc. threw NullPointerException instead of behaving like an unset field.
    private Optional<String> accountNumber = Optional.empty();
    private Optional<String> customerId = Optional.empty();
    private Optional<String> currency = Optional.empty();
    private Optional<String> customerEmail = Optional.empty();

    public PaymentRequest(double amount){
        this.amount = amount;
    }

    public void setAccountNumber(Optional<String> accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCustomerId(Optional<String> customerId) {
        this.customerId = customerId;
    }

    public void setCurrency(Optional<String> currency) {
        this.currency = currency;
    }

    public void setCustomerEmail(Optional<String> customerEmail) {
        this.customerEmail = customerEmail;
    }

    public double getAmount() {
        return amount;
    }

    public Optional<String> getAccountNumber() {
        return accountNumber;
    }

    public Optional<String> getCustomerId() {
        return customerId;
    }

    public Optional<String> getCurrency() {
        return currency;
    }

    public Optional<String> getCustomerEmail() {
        return customerEmail;
    }
}
