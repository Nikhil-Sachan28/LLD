package dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.enums.PaymentStatus;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.exceptions.MissingDataException;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.models.PaymentRequest;
import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.models.PaymentResult;

/**
 * Target interface for the Adapter pattern.
 *
 * IMPORTANT: this interface must stay a pure business/domain contract.
 * It previously also declared takeInput(Scanner, PaymentRequest), which
 * leaked a CLI/presentation concern into the domain abstraction and broke
 * Interface Segregation. That responsibility now lives in the separate
 * PaymentInputCollector interface, implemented only by adapters that need
 * to collect console input.
 */
public interface PaymentGateway {

    PaymentResult pay(PaymentRequest request) throws MissingDataException;

    PaymentStatus getPaymentStatus(String transactionId);

    /**
     * Returns the normalized outcome of the refund (SUCCESS / FAILED / PENDING)
     * instead of silently discarding the provider's success/failure signal.
     */
    PaymentStatus refund(String transactionId, double amount);
}
