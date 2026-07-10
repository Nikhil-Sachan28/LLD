# CyclicBarrier

## What is CyclicBarrier?

`CyclicBarrier` is a synchronization utility in Java that allows a fixed number of threads to wait for each other at a common synchronization point (called a **barrier**) before all of them continue execution.

Unlike `CountDownLatch`, where one thread waits for other threads to finish, **in a `CyclicBarrier`, every participating thread waits** until the required number of threads arrive at the barrier.

It is part of the `java.util.concurrent` package.

---

# Real-Life Analogy

Imagine three friends planning to watch a movie together.

- Friend A reaches the theater.
- Friend B reaches the theater.
- Friend C reaches the theater.

Nobody enters until **all three friends have arrived**.

```
Friend A --------\
                  \
Friend B ---------> Barrier
                  /
Friend C --------/

       |
       V

Everyone enters together.
```

After everyone crosses the barrier, it automatically resets and can be used again.

---

# When to Use CyclicBarrier

Use `CyclicBarrier` when:

- Multiple threads must reach the same point before continuing.
- Every thread depends on every other thread reaching a checkpoint.
- The synchronization needs to happen repeatedly.
- Threads execute work in multiple phases.

Common examples:

- Multiplayer games (all players ready before the game starts)
- Scientific simulations
- Parallel matrix multiplication
- Multi-stage computations
- H₂O problem (2 Hydrogen + 1 Oxygen form one molecule)
- Machine learning batch processing

---

# Important Constructors

## Constructor

```java
CyclicBarrier barrier = new CyclicBarrier(parties);
```

Example:

```java
CyclicBarrier barrier = new CyclicBarrier(3);
```

The barrier opens only after **3 threads** reach it.

---

## Constructor with Barrier Action

```java
CyclicBarrier barrier =
        new CyclicBarrier(3, () -> System.out.println("All threads arrived!"));
```

The second argument is called the **Barrier Action**.

It executes exactly once after the last thread reaches the barrier and before all waiting threads continue.

---

# Important Methods

## await()

```java
barrier.await();
```

Blocks the current thread until all participating threads reach the barrier.

---

## getNumberWaiting()

Returns the number of threads currently waiting.

```java
System.out.println(barrier.getNumberWaiting());
```

---

## getParties()

Returns the total number of participating threads.

```java
System.out.println(barrier.getParties());
```

---

## isBroken()

Returns whether the barrier has been broken.

```java
barrier.isBroken();
```

---

## reset()

Resets the barrier so it can be reused.

```java
barrier.reset();
```

---

# Example

```java
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(
                3,
                () -> System.out.println("\nAll workers reached the barrier.\n")
        );

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task = () -> {

            try {

                System.out.println(Thread.currentThread().getName() + " doing work");

                Thread.sleep((long)(Math.random() * 3000));

                System.out.println(Thread.currentThread().getName() + " waiting");

                barrier.await();

                System.out.println(Thread.currentThread().getName() + " continues");

            } catch (Exception e) {
                e.printStackTrace();
            }

        };

        executor.execute(task);
        executor.execute(task);
        executor.execute(task);

        executor.shutdown();
    }
}
```

---

# Possible Output

```
pool-1-thread-2 doing work
pool-1-thread-1 doing work
pool-1-thread-3 doing work

pool-1-thread-3 waiting
pool-1-thread-1 waiting
pool-1-thread-2 waiting

All workers reached the barrier.

pool-1-thread-3 continues
pool-1-thread-1 continues
pool-1-thread-2 continues
```

Notice that **no thread continues until all three reach the barrier.**

---

# How It Works

Suppose the barrier is created as:

```java
CyclicBarrier barrier = new CyclicBarrier(3);
```

### Step 1

```
Thread A arrives

Waiting = 1
```

---

### Step 2

```
Thread B arrives

Waiting = 2
```

---

### Step 3

```
Thread C arrives

Waiting = 3
```

Now:

- Barrier opens.
- All three threads continue.
- Barrier automatically resets.

---

# Flow Diagram

```
Thread A --------\
                  \
Thread B ---------> CyclicBarrier (3)
                  /
Thread C --------/

        |
        V

All continue together
```

---

# Barrier Action

A barrier action runs **once** when the last thread reaches the barrier.

Example:

```java
CyclicBarrier barrier =
        new CyclicBarrier(3, () -> {
            System.out.println("Starting next phase...");
        });
```

Execution order:

```
Thread A waiting
Thread B waiting
Thread C waiting

↓

Barrier Action

↓

All threads continue
```

---

# Characteristics

- Every participating thread waits.
- Automatically reusable.
- Supports multiple synchronization phases.
- Can execute a barrier action.
- Number of participating threads is fixed.

---

# Difference Between CyclicBarrier and CountDownLatch

| Feature | CyclicBarrier | CountDownLatch |
|----------|---------------|----------------|
| Purpose | Make threads wait for each other | Make one or more threads wait until tasks finish |
| Who waits? | All participating threads | Usually one (or more) waiting thread |
| Counter | Number of participating threads | Number of remaining tasks/events |
| Reusable | ✅ Yes | ❌ No |
| Reset automatically | ✅ Yes | ❌ No |
| Reset manually | ✅ `reset()` | ❌ Not possible |
| Barrier Action | ✅ Supported | ❌ Not supported |
| Synchronization style | Peer-to-peer | Parent-child (workers notify waiter) |

---

# CountDownLatch Example

```
Worker 1 -----\
Worker 2 ------> Main Thread waits
Worker 3 -----/

Workers finish one by one

↓

Main thread continues
```

Only the **main thread waits**.

---

# CyclicBarrier Example

```
Worker 1 ----\
              \
Worker 2 ------> Barrier
              /
Worker 3 ----/

↓

All workers continue together
```

Everyone waits.

---

# Real-World Use Cases

## CountDownLatch

- Application startup
- Waiting for multiple APIs
- Loading configuration
- Database initialization
- Parallel file downloads
- Integration testing

Pattern:

```
Workers finish

↓

Main thread starts
```

---

## CyclicBarrier

- Multiplayer game rounds
- Scientific simulations
- Machine learning iterations
- Matrix multiplication
- Distributed computations
- H₂O molecule formation
- Multi-phase algorithms

Pattern:

```
Everyone waits

↓

Everyone continues

↓

Repeat
```

---

# Advantages

- Reusable
- Simple API
- Supports repeated synchronization
- Barrier action simplifies coordination
- Excellent for phase-based processing

---

# Limitations

- Number of participating threads is fixed.
- If one thread never reaches the barrier, the others wait indefinitely.
- If one waiting thread is interrupted or times out, the barrier becomes broken.

---

# Interview Questions

## Is `await()` blocking?

Yes.

Every participating thread blocks until all required threads arrive.

---

## Can CyclicBarrier be reused?

Yes.

After all waiting threads are released, it automatically resets for the next cycle.

---

## Can the number of parties change?

No.

It is fixed when the barrier is created.

---

## What is a Broken Barrier?

If one waiting thread is interrupted or times out, the barrier is broken and the remaining waiting threads receive a `BrokenBarrierException`.

---

## When should I choose CountDownLatch?

When one thread depends on multiple tasks finishing.

---

## When should I choose CyclicBarrier?

When multiple threads need to meet at a synchronization point before continuing together.

---

# Summary

- `CyclicBarrier` synchronizes **peer threads**.
- Every participating thread waits at `await()`.
- When the required number of threads arrives, everyone continues.
- It automatically resets after each cycle.
- Supports a barrier action executed once per cycle.
- Best suited for repeated, phase-based concurrent algorithms.