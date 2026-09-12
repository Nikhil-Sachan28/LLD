# Decorator Pattern Assignment — API Request Processing Pipeline

## Objective

Build an **API Request Processing Pipeline** using the **Decorator Design Pattern**.

The system should allow different request-processing behaviors to be added dynamically without modifying the core request handler.

Use **plain Java only**. Do not use Spring or any framework.

---

# 1. Core Abstraction

Create:

```java
public interface RequestHandler {
    Response handle(Request request);
}
```

Create a basic implementation:

```java
public class BasicRequestHandler implements RequestHandler {

    @Override
    public Response handle(Request request) {
        // Actual request processing
    }
}
```

---

# 2. Required Decorators

Implement at least the following decorators.

## AuthenticationDecorator

Responsibilities:

* Validate the authentication token.
* If authentication fails, return `UNAUTHORIZED`.
* If authentication succeeds, forward the request.

---

## RateLimitDecorator

Responsibilities:

* Track requests per client.
* Reject requests when the client exceeds the allowed limit.
* Otherwise forward the request.

Example:

```text
Client A
    ↓
Request 1 → allowed
Request 2 → allowed
Request 3 → allowed
Request 4 → RATE_LIMITED
```

---

## LoggingDecorator

Log:

* Request ID
* Endpoint
* Processing time
* Response status

The decorator should measure the execution time of the wrapped handler.

---

## CachingDecorator

Responsibilities:

* Cache successful responses.
* Return the cached response when available.
* Do not call the wrapped handler on a cache hit.

Example:

```text
Request
   ↓
Cache hit?
   ├── YES → return cached response
   └── NO  → call next handler
```

Create a meaningful cache key, for example:

```text
clientId + endpoint + request parameters
```

---

## RetryDecorator

Responsibilities:

* Retry failed requests up to `N` times.
* Retry only appropriate transient/server failures.
* Do not retry authentication failures.
* Do not retry rate-limit failures.

Example:

```text
Request
   ↓
SERVER_ERROR
   ↓
Retry
   ↓
SERVER_ERROR
   ↓
Retry
   ↓
SUCCESS
```

---

## MetricsDecorator

Track at least:

```text
Total requests
Successful requests
Failed requests
Average response time
```

---

# 3. Response Status

Create something similar to:

```java
public enum ResponseStatus {

    SUCCESS,
    UNAUTHORIZED,
    RATE_LIMITED,
    BAD_REQUEST,
    SERVER_ERROR
}
```

You may add additional statuses if required by your design.

---

# 4. Decorator Composition

The decorators must be composable.

For example:

```java
RequestHandler handler =
        new MetricsDecorator(
            new LoggingDecorator(
                new RetryDecorator(
                    new CachingDecorator(
                        new AuthenticationDecorator(
                            new RateLimitDecorator(
                                new BasicRequestHandler()
                            )
                        )
                    )
                )
            )
        );
```

The client should only interact with:

```java
RequestHandler
```

It should not need to know about the internal pipeline.

For example:

```java
Response response = handler.handle(request);
```

---

# 5. Important Requirements

### Decorator requirements

Each decorator must:

* Implement `RequestHandler`.
* Contain another `RequestHandler`.
* Delegate to the wrapped handler when appropriate.
* Be independently composable.
* Be usable with other decorators without modification.

---

### Do NOT do this

Avoid a design like:

```java
if (loggingEnabled) {
    // logging
}

if (cacheEnabled) {
    // caching
}

if (retryEnabled) {
    // retry
}

if (authenticationEnabled) {
    // authentication
}
```

The goal is to add behavior through **composition**, not conditional logic inside the core handler.

---

# 6. Failure Handling

Your decorators should be able to short-circuit the pipeline.

For example:

```text
Authentication fails
        ↓
UNAUTHORIZED
        ↓
Do not call next handler
```

```text
Rate limit exceeded
        ↓
RATE_LIMITED
        ↓
Do not call next handler
```

```text
Cache hit
        ↓
Return cached response
        ↓
Do not call next handler
```

```text
SERVER_ERROR
        ↓
RetryDecorator
        ↓
Retry if appropriate
```

---

# 7. Design Challenge

Think carefully about **decorator ordering**.

For example:

```text
Logging
   ↓
Caching
   ↓
Authentication
   ↓
BasicHandler
```

is not necessarily equivalent to:

```text
Authentication
   ↓
Logging
   ↓
Caching
   ↓
BasicHandler
```

Consider:

* Should authentication happen before caching?
* Should unauthorized responses ever be cached?
* Should rate limiting happen before or after caching?
* Should a cache hit count toward rate limiting?
* Should retries be logged as one request or multiple attempts?
* Should metrics measure the entire pipeline or individual attempts?
* What happens when a decorator short-circuits the request?
* Where should cache state live?
* Where should rate-limit state live?

Make reasonable design decisions and be prepared to explain them.

---

# 8. Constraints

* Java only.
* No Spring.
* No external frameworks.
* Do not modify `BasicRequestHandler` when adding new decorators.
* Do not create subclasses for every possible combination.
* Avoid unnecessary inheritance.
* Avoid a single giant class containing all behaviors.
* Decorators should remain independently reusable.

---

# 9. Expected Architecture

```text
                     Client
                       |
                       v
                RequestHandler
                       |
                       v
              MetricsDecorator
                       |
                       v
              LoggingDecorator
                       |
                       v
               RetryDecorator
                       |
                       v
              CachingDecorator
                       |
                       v
          AuthenticationDecorator
                       |
                       v
            RateLimitDecorator
                       |
                       v
             BasicRequestHandler
```

The exact ordering is your design decision.

---

# 10. Deliverables

Implement:

```text
RequestHandler
BasicRequestHandler

AuthenticationDecorator
RateLimitDecorator
LoggingDecorator
CachingDecorator
RetryDecorator
MetricsDecorator

Request
Response
ResponseStatus
```

Also create a small `Application`/`Main` class that demonstrates:

1. Successful request.
2. Authentication failure.
3. Rate-limit failure.
4. Cache hit.
5. Server failure followed by retry.
6. Multiple decorators working together.

---

# 11. Bonus

Add:

```java
AuthorizationDecorator
```

It should check whether the authenticated user has permission to access a particular endpoint.

For example:

```text
USER → /profile     → allowed
USER → /admin       → forbidden
ADMIN → /admin      → allowed
```

Do this **without modifying the existing decorators or `BasicRequestHandler`**.

---

# Goal

The important part is **not the number of decorators**.

I want you to demonstrate that you understand:

```text
Composition
    ↓
Dynamic behavior
    ↓
Open/Closed Principle
    ↓
Low coupling
    ↓
Independent reusable behaviors
```

The final system should make it easy to add something like:

```text
CompressionDecorator
TracingDecorator
AuthorizationDecorator
CircuitBreakerDecorator
```

without rewriting the existing request-processing logic.
