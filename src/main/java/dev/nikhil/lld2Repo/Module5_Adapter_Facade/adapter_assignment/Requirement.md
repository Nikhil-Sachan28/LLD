# Your task

Design the system so that this works:
```
PaymentGateway gateway = paymentGatewayFactory.getGateway("RAZORPAY");

PaymentResult result = gateway.pay(request);
```
And the application must not care whether the underlying provider is Razorpay, Stripe, or the legacy system.


## Requirements
1. Create appropriate adapters for the external payment APIs.
2. Do not modify the existing provider classes.
3. Your adapters must implement PaymentGateway.
4. Handle differences such as:
- - amount conversion (₹100 → paise)
- - different customer information
- - different transaction IDs
- - different status representations
- - different refund APIs

5. Convert provider-specific responses into your application's:
```
PaymentResult
PaymentStatus
```
6. The application layer should have zero knowledge of:
- RazorpayClient
- StripeClient
- LegacyPaymentSystem

7. Don't use if/else or switch inside your business/service layer to determine which provider to call.

8. You should be able to add:
```
PayU
Cashfree
Adyen
```
later without modifying the existing payment business logic.

## Important constraint

Don't create one giant adapter like:
```
PaymentAdapter
```
that internally handles all three providers.

You should figure out the appropriate adapter structure yourself.

## Bonus challenge 🔥

Design it so that the following provider-specific mess stays completely outside your application domain:
```
Razorpay → paise
Stripe   → smallest currency unit
Legacy   → double
```
and:
```
Razorpay → "CREATED", "CAPTURED", "FAILED"
Stripe   → ChargeStatus enum
Legacy   → "SUCCESS", "FAILED", "PENDING"
```
Your domain should see only something like:
```
PaymentStatus.SUCCESS
PaymentStatus.FAILED
PaymentStatus.PENDING
```