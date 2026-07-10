# CountDownLatch

## What is CountDownLatch?

`CountDownLatch` is a synchronization utility in Java that allows one or more threads to wait until a set of operations being performed by other threads completes.

Think of it as a **countdown timer**. A thread waits until the counter reaches **0**, and other threads decrease the counter as they finish their work.

It is part of the `java.util.concurrent` package.

---

## Real-Life Analogy

Imagine a rocket launch.

Before launch, three systems must report that they are ready:

- Fuel System
- Navigation System
- Communication System

The launch controller waits until all three systems finish their checks.

```text
Fuel Ready --------\
                    \
Navigation Ready -----> Launch Rocket
                    /
Communication Ready /
```

The launch controller does not proceed until every subsystem is ready.

---

## When to Use CountDownLatch

Use `CountDownLatch` when:

- A thread must wait for multiple threads to complete.
- You want to coordinate the startup of several tasks.
- You need to wait for initialization before continuing.
- You want to wait for multiple background tasks to finish.

Common examples:

- Waiting for multiple microservices to initialize.
- Loading configuration files.
- Database connection initialization.
- Running multiple tasks in parallel and waiting for all results.

---

## Important Methods

### Constructor

```java
CountDownLatch latch = new CountDownLatch(count);
```

Example:

```java
CountDownLatch latch = new CountDownLatch(3);
```

This means the latch will wait for **3 events**.

---

### await()

```java
latch.await();
```

Blocks the current thread until the count reaches zero.

Example:

```java
latch.await();
System.out.println("All tasks completed.");
```

---

### countDown()

```java
latch.countDown();
```

Decreases the count by one.

If the count becomes zero, all waiting threads are released.

Example:

```java
latch.countDown();
```

---

### getCount()

Returns the current count.

```java
System.out.println(latch.getCount());
```

---

## Example

```java
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws Exception {

        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " started");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println(Thread.currentThread().getName() + " finished");

            latch.countDown();
        };

        executor.execute(task);
        executor.execute(task);
        executor.execute(task);

        System.out.println("Main thread waiting...");

        latch.await();

        System.out.println("All tasks completed.");

        executor.shutdown();
    }
}
```

### Possible Output

```
Main thread waiting...
pool-1-thread-1 started
pool-1-thread-2 started
pool-1-thread-3 started

pool-1-thread-2 finished
pool-1-thread-1 finished
pool-1-thread-3 finished

All tasks completed.
```

---

## How It Works

Initial count:

```
3
```

Task 1 finishes:

```
countDown()

Count = 2
```

Task 2 finishes:

```
countDown()

Count = 1
```

Task 3 finishes:

```
countDown()

Count = 0
```

Now the thread waiting on `await()` is released.

---

## Flow Diagram

```
Worker 1 --------\
                  \
Worker 2 ---------> CountDownLatch (3)
                  /
Worker 3 --------/

          |
          |
          V

Main Thread continues
```

---

## Characteristics

- One or more threads can wait.
- Multiple threads can call `countDown()`.
- The counter only decreases.
- Once the counter reaches zero, it cannot be reset.
- It is a one-time synchronization aid.

---

## Difference Between CountDownLatch and Semaphore

| CountDownLatch | Semaphore |
|---------------|-----------|
| Waits for events to complete | Controls access to a shared resource |
| Uses `await()` | Uses `acquire()` |
| Uses `countDown()` | Uses `release()` |
| Counter only decreases | Permits can increase and decrease |
| One-time use | Reusable |
| Coordinates thread completion | Limits concurrent access |

---

## Difference Between CountDownLatch and CyclicBarrier

| CountDownLatch | CyclicBarrier |
|---------------|---------------|
| One or more threads wait | All participating threads wait |
| Counter cannot be reset | Automatically resets after every cycle |
| One-time synchronization | Reusable synchronization |
| Uses `countDown()` | Uses `await()` |
| Best for waiting for tasks to finish | Best for making threads reach the same point together |

---

## Advantages

- Easy to understand.
- Simple API.
- Excellent for coordinating multiple worker threads.
- No busy waiting.
- Commonly used in production systems.

---

## Limitations

- Cannot increase the count.
- Cannot reset once the count reaches zero.
- One-time use only.
- If reusability is required, use `CyclicBarrier` or `Phaser`.

---

## Real-World Use Cases

- Application startup.
- Waiting for multiple API calls.
- Waiting for cache initialization.
- Parallel file processing.
- Database migration before application startup.
- Integration testing.
- Waiting for background jobs.

---

## Interview Questions

### Is `await()` blocking?

Yes.

It blocks until the count becomes zero.

---

### Is `countDown()` blocking?

No.

It simply decreases the count and returns immediately.

---

### Can the count be increased?

No.

The count can only decrease.

---

### Can CountDownLatch be reused?

No.

Create a new `CountDownLatch` if needed again.

---

### Which thread calls `await()`?

Usually the main thread, but any thread can call it.

---

### Which threads call `countDown()`?

The worker threads that perform the tasks.

---

## Summary

- `CountDownLatch` is used to wait for one or more events.
- `await()` blocks until the count becomes zero.
- `countDown()` decreases the count.
- It is a one-time synchronization utility.
- It is commonly used when one thread depends on the completion of several other threads.