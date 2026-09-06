# Java ServiceLoader — Notes

## 1. What is ServiceLoader?

`ServiceLoader` is a built-in Java mechanism for **discovering implementations of an interface at runtime**.

Instead of hard-coding:

```java
PaymentGateway gateway = new StripePaymentGateway();
```

we can ask Java:

> "Find the implementations of `PaymentGateway` that have been registered as services."

```java
ServiceLoader<PaymentGateway> loader =
        ServiceLoader.load(PaymentGateway.class);

for (PaymentGateway gateway : loader) {
    gateway.pay(1000);
}
```

### Mental model

```text
Interface
    ↓
ServiceLoader
    ↓
Discover registered implementations
    ↓
Load provider classes
    ↓
Create/use provider objects
```

---

# 2. Why do we need it?

Suppose we have a payment system.

```text
PaymentGateway
    ├── StripePaymentGateway
    ├── PayPalPaymentGateway
    └── RazorpayPaymentGateway
```

Without `ServiceLoader`, we might write:

```java
if (provider.equals("stripe")) {
    return new StripePaymentGateway();
} else if (provider.equals("paypal")) {
    return new PayPalPaymentGateway();
}
```

Now suppose another team creates:

```java
AcmePaymentGateway
```

We would have to modify our core application.

With `ServiceLoader`, another team can package its implementation as a separate JAR and register it as a provider.

The core application doesn't need to directly reference that concrete class.

---

# 3. Basic Example

## Step 1: Create the interface

```java
public interface PaymentGateway {

    void pay(int amount);
}
```

This is called the **service interface**.

---

## Step 2: Create an implementation

```java
public class StripePaymentGateway implements PaymentGateway {

    @Override
    public void pay(int amount) {
        System.out.println(
            "Payment of " + amount + " made using Stripe"
        );
    }
}
```

This is called the **service provider** or **provider implementation**.

---

# 4. Register the Provider

Java needs to know that:

```text
StripePaymentGateway
```

implements:

```text
PaymentGateway
```

For a classpath-based application, create:

```text
src/
└── main/
    └── resources/
        └── META-INF/
            └── services/
                └── com.example.PaymentGateway
```

The filename must be the **fully qualified name of the interface**.

For example:

```text
com.example.PaymentGateway
```

Inside that file:

```text
com.example.StripePaymentGateway
```

So the structure is:

```text
META-INF/
└── services/
    └── com.example.PaymentGateway
            ↓
    com.example.StripePaymentGateway
```

---

# 5. Discover the Provider

Now:

```java
ServiceLoader<PaymentGateway> loader =
        ServiceLoader.load(PaymentGateway.class);

for (PaymentGateway gateway : loader) {
    gateway.pay(1000);
}
```

Output:

```text
Payment of 1000 made using Stripe
```

Notice:

We never wrote:

```java
new StripePaymentGateway();
```

The application only knows:

```java
PaymentGateway
```

`ServiceLoader` discovers the concrete implementation.

---

# 6. What Happens Internally?

Think of it like this:

```text
ServiceLoader.load(PaymentGateway.class)
                    ↓
            Look for services
                    ↓
       META-INF/services/
                    ↓
       com.example.PaymentGateway
                    ↓
       StripePaymentGateway
                    ↓
            Load the class
                    ↓
          Create provider
                    ↓
              Use provider
```

So:

```java
ServiceLoader<PaymentGateway>
```

doesn't contain Stripe or PayPal itself.

It **discovers** them.

---

# 7. Multiple Implementations

One of the most useful features of `ServiceLoader` is that there can be **multiple providers**.

Suppose:

```java
public class StripePaymentGateway
        implements PaymentGateway {

    @Override
    public void pay(int amount) {
        System.out.println("Stripe: " + amount);
    }
}
```

and:

```java
public class PayPalPaymentGateway
        implements PaymentGateway {

    @Override
    public void pay(int amount) {
        System.out.println("PayPal: " + amount);
    }
}
```

The service configuration can contain:

```text
com.example.StripePaymentGateway
com.example.PayPalPaymentGateway
```

Then:

```java
ServiceLoader<PaymentGateway> loader =
        ServiceLoader.load(PaymentGateway.class);

for (PaymentGateway gateway : loader) {
    gateway.pay(1000);
}
```

could produce:

```text
Stripe: 1000
PayPal: 1000
```

Therefore:

> **ServiceLoader is primarily a discovery mechanism.**

It can discover multiple implementations.

---

# 8. Selecting One Provider

Sometimes we don't want all providers.

We want:

```text
"stripe" → StripePaymentGateway

"paypal" → PayPalPaymentGateway
```

We can add metadata to our interface:

```java
public interface PaymentGateway {

    String name();

    void pay(int amount);
}
```

Stripe:

```java
public class StripePaymentGateway
        implements PaymentGateway {

    @Override
    public String name() {
        return "stripe";
    }

    @Override
    public void pay(int amount) {
        System.out.println("Stripe payment: " + amount);
    }
}
```

Then:

```java
ServiceLoader<PaymentGateway> loader =
        ServiceLoader.load(PaymentGateway.class);

PaymentGateway selected = null;

for (PaymentGateway gateway : loader) {

    if (gateway.name().equals("stripe")) {
        selected = gateway;
        break;
    }
}

if (selected != null) {
    selected.pay(1000);
}
```

Important distinction:

```text
ServiceLoader
     ↓
DISCOVERS providers

Application
     ↓
SELECTS provider
```

---

# 9. ServiceLoader Is NOT a Factory

This is very important.

### ServiceLoader asks:

> **"Which implementations are available?"**

### Factory asks:

> **"Which implementation should I create/use?"**

For example:

```java
ServiceLoader<PaymentGateway> loader =
        ServiceLoader.load(PaymentGateway.class);
```

might discover:

```text
StripePaymentGateway
PayPalPaymentGateway
RazorpayPaymentGateway
```

Then a factory/registry could select:

```text
"stripe"
    ↓
StripePaymentGateway
```

So they can work together.

But:

> **ServiceLoader itself is not a GoF Factory.**

---

# 10. ServiceLoader Is NOT Factory Method

GoF Factory Method uses **polymorphism and inheritance**.

Example:

```java
abstract class PaymentService {

    public void processPayment(int amount) {

        validate(amount);

        PaymentGateway gateway = createGateway();

        gateway.pay(amount);
    }

    protected abstract PaymentGateway createGateway();

    private void validate(int amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Invalid amount"
            );
        }
    }
}
```

A subclass decides what gets created:

```java
class StripePaymentService
        extends PaymentService {

    @Override
    protected PaymentGateway createGateway() {
        return new StripePaymentGateway();
    }
}
```

Here:

```text
PaymentService
       ↓
createGateway()
       ↓
StripePaymentGateway
```

That is **Factory Method**.

ServiceLoader is different:

```text
PaymentGateway.class
       ↓
ServiceLoader
       ↓
Discover providers
```

---

# 11. ServiceLoader and DIP

DIP says:

> High-level modules should depend on abstractions rather than concrete implementations.

Example:

```java
public class PaymentService {

    private final PaymentGateway gateway;

    public PaymentService(PaymentGateway gateway) {
        this.gateway = gateway;
    }
}
```

`PaymentService` depends on:

```java
PaymentGateway
```

instead of:

```java
StripePaymentGateway
```

ServiceLoader can help maintain this separation because the core application only needs to know the interface.

But remember:

```text
DIP
↓
Design principle

ServiceLoader
↓
Java provider-discovery mechanism
```

They are related, but they are **not the same thing**.

---

# 12. Real-World Architecture

Imagine:

```text
payment-core.jar
```

contains:

```text
PaymentGateway
PaymentService
```

Another team creates:

```text
stripe-provider.jar
```

containing:

```text
StripePaymentGateway
META-INF/services/com.example.PaymentGateway
```

Another team creates:

```text
paypal-provider.jar
```

containing:

```text
PayPalPaymentGateway
META-INF/services/com.example.PaymentGateway
```

Deployment:

```text
Application
│
├── payment-core.jar
├── stripe-provider.jar
└── paypal-provider.jar
```

The core application doesn't need:

```java
new StripePaymentGateway();
```

or:

```java
new PayPalPaymentGateway();
```

Instead:

```java
ServiceLoader<PaymentGateway> loader =
        ServiceLoader.load(PaymentGateway.class);
```

discovers them.

---

# 13. Different Teams Can Add Providers

This is one of the strongest use cases.

Suppose:

### Team A

Owns:

```text
payment-core.jar
```

It defines:

```java
public interface PaymentGateway {
    void pay(int amount);
}
```

### Team B

Creates:

```text
razorpay-provider.jar
```

with:

```java
public class RazorpayPaymentGateway
        implements PaymentGateway {

    @Override
    public void pay(int amount) {
        System.out.println("Razorpay: " + amount);
    }
}
```

and registers:

```text
META-INF/services/com.example.PaymentGateway
```

containing:

```text
com.example.RazorpayPaymentGateway
```

Team A doesn't need to modify its core code.

This gives us:

```text
Core application
       ↑
       |
    Interface
       |
       ↑
External provider JAR
```

The provider is **pluggable**.

---

# 14. Does It Work While the Backend Is Already Running?

Normally:

**No.**

Suppose your backend is already running:

```text
Backend JVM
   │
   ├── payment-core.jar
   └── stripe-provider.jar
```

You now edit:

```text
META-INF/services/com.example.PaymentGateway
```

and add:

```text
com.example.PayPalPaymentGateway
```

That doesn't mean the running backend will automatically start using PayPal.

Two things are involved:

```text
1. Provider configuration
2. Provider class/JAR
```

The actual provider class must also be available to the relevant classloader.

The normal production flow is:

```text
Create provider
      ↓
Build JAR
      ↓
Deploy JAR
      ↓
Restart/reload application
      ↓
ServiceLoader discovers provider
```

Therefore:

> **ServiceLoader provides extensibility, not general-purpose hot reloading.**

---

# 15. What About `reload()`?

`ServiceLoader` has:

```java
loader.reload();
```

Example:

```java
ServiceLoader<PaymentGateway> loader =
        ServiceLoader.load(PaymentGateway.class);

loader.reload();
```

This clears the provider cache and allows the loader to reconsider provider configuration.

However:

```java
reload()
```

does **not** magically make arbitrary new classes available to an existing classloader.

So don't think:

```text
Edit META-INF
      ↓
loader.reload()
      ↓
Everything magically appears
```

Instead, in a normal production deployment:

```text
New provider
      ↓
Deploy
      ↓
Restart/reload application
      ↓
ServiceLoader discovers it
```

---

# 16. Lazy Discovery

`ServiceLoader` is generally lazy.

When you write:

```java
ServiceLoader<PaymentGateway> loader =
        ServiceLoader.load(PaymentGateway.class);
```

you haven't necessarily created every provider immediately.

Providers are discovered/instantiated as you iterate:

```java
for (PaymentGateway gateway : loader) {
    gateway.pay(100);
}
```

This can be useful when there are many providers.

---

# 17. `ServiceLoader.Provider`

Modern Java also provides:

```java
ServiceLoader.Provider<T>
```

through:

```java
loader.stream()
```

Example:

```java
ServiceLoader<PaymentGateway> loader =
        ServiceLoader.load(PaymentGateway.class);

loader.stream()
      .forEach(provider ->
          System.out.println(
              provider.type().getName()
          ));
```

This allows you to inspect provider types before necessarily creating the provider instance.

---

# 18. Java Modules

With Java 9+, the module system can also declare services.

Consumer:

```java
module payment.core {

    uses com.example.PaymentGateway;
}
```

Provider:

```java
module stripe.provider {

    provides com.example.PaymentGateway
        with com.example.StripePaymentGateway;
}
```

The application can still use:

```java
ServiceLoader<PaymentGateway> loader =
        ServiceLoader.load(PaymentGateway.class);
```

If you're currently learning Java/Spring Boot, don't worry too much about modules yet.

First remember:

```text
Interface
    ↓
Provider implementation
    ↓
Register provider
    ↓
ServiceLoader
    ↓
Discover provider
```

---

# 19. ServiceLoader vs Dependency Injection

In Spring Boot, you will normally use Spring DI for ordinary application dependencies:

```java
@Service
public class PaymentService {

    private final PaymentGateway gateway;

    public PaymentService(PaymentGateway gateway) {
        this.gateway = gateway;
    }
}
```

Spring manages:

```text
Creation
Lifecycle
Injection
Configuration
```

For normal Spring applications, this is usually simpler than using `ServiceLoader`.

ServiceLoader becomes particularly useful when you need:

```text
External plugins
       +
Independent JARs
       +
Provider discovery
       +
Core application doesn't know
the concrete implementations
```

So don't think:

> "ServiceLoader is better than Spring DI."

Think:

> "ServiceLoader is a Java SPI mechanism, especially useful for plugin/provider architectures."

---

# 20. When Should I Use ServiceLoader?

## Good use cases

Use it when:

```text
✓ You have a plugin architecture
✓ Providers come from separate JARs
✓ Different teams/vendors provide implementations
✓ The core application shouldn't hard-code providers
✓ You need Java SPI/provider discovery
```

Examples:

```text
Payment providers
Database providers
Logging providers
IDE plugins
File-format providers
Encryption providers
Framework extensions
Cloud integrations
```

## Probably DON'T use it when:

```text
✗ You have only 2–3 internal implementations
✗ Spring DI already solves the problem
✗ You simply need runtime selection
✗ A Map<String, Interface> is simpler
✗ You don't actually need external plugins
```

---

# 21. ServiceLoader vs Other Concepts

| Concept              | Main Question                                     |
| -------------------- | ------------------------------------------------- |
| `new`                | How do I create this object?                      |
| Simple Factory       | Which implementation should I create?             |
| Factory Method       | Which subclass decides product creation?          |
| Abstract Factory     | Which family of related products should I create? |
| Dependency Injection | Who supplies my dependency?                       |
| `ServiceLoader`      | Which implementations/providers are available?    |

The most important word for `ServiceLoader` is:

# **DISCOVERY**

---

# 22. The Most Important Mental Model

Remember:

```text
              SERVICE INTERFACE
                     |
                     ↓
              PaymentGateway
                     |
                     ↓
               ServiceLoader
                     |
          ┌──────────┼──────────┐
          ↓          ↓          ↓
       Stripe      PayPal    Razorpay
```

The application says:

> "I know the interface. I don't need to know every implementation."

The provider says:

> "I implement the interface and register myself."

ServiceLoader says:

> "I'll discover the providers that are available."

---

# 23. Interview Answer

### What is ServiceLoader?

> `ServiceLoader` is Java's built-in SPI mechanism for discovering implementations of an interface that are registered as service providers. It allows applications and frameworks to support independently packaged implementations without hard-coding concrete classes.

### Is ServiceLoader a Factory?

> No. ServiceLoader is primarily a provider-discovery mechanism. A factory is responsible for encapsulating or deciding object creation. They can be combined, but they solve different problems.

---

# 24. Final Cheat Sheet

```text
ServiceLoader
      ↓
Java SPI
      ↓
Provider Discovery
      ↓
Find implementations of an interface
      ↓
META-INF/services/<interface>
      ↓
Load provider classes
      ↓
Use providers
```

### Remember these 5 words:

**Interface → Register → Discover → Load → Use**

### One-line definition

> **ServiceLoader lets Java discover independently packaged implementations of an interface at runtime, making plugin/provider architectures possible without hard-coding those implementations into the core application.**

---

# 25. Tiny Complete Example

### Interface

```java
public interface PaymentGateway {

    void pay(int amount);
}
```

### Provider

```java
public class StripePaymentGateway
        implements PaymentGateway {

    @Override
    public void pay(int amount) {
        System.out.println(
            "Paid " + amount + " using Stripe"
        );
    }
}
```

### Registration

File:

```text
META-INF/services/com.example.PaymentGateway
```

Contents:

```text
com.example.StripePaymentGateway
```

### Application

```java
import java.util.ServiceLoader;

public class Main {

    public static void main(String[] args) {

        ServiceLoader<PaymentGateway> loader =
                ServiceLoader.load(PaymentGateway.class);

        for (PaymentGateway gateway : loader) {
            gateway.pay(1000);
        }
    }
}
```

Output:

```text
Paid 1000 using Stripe
```

The application never directly wrote:

```java
new StripePaymentGateway();
```

**That is the core idea of `ServiceLoader`.**
