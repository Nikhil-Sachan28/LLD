package dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.adapter;

import dev.nikhil.lld2Repo.Module5_Adapter_Facade.adapter_assignment.models.PaymentRequest;

import java.util.Scanner;

/**
 * Presentation-layer concern, intentionally kept OUT of PaymentGateway.
 *
 * Only the CLI (Application.java) needs this. A REST controller, a batch job,
 * or a test would build a PaymentRequest directly and would never need to
 * depend on Scanner-based input collection at all.
 */
public interface PaymentInputCollector {
    void collect(Scanner sc, PaymentRequest pr);
}
