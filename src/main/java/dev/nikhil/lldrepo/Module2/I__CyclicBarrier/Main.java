package dev.nikhil.lldrepo.Module2.I__CyclicBarrier;


import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(3);
        CyclicBarrier latch = new CyclicBarrier(
                3,
                ()-> System.out.println("Bus starts")
                );// "I am waiting for 3 events."
        TaskNeededToBeCompleted tasks = new TaskNeededToBeCompleted(latch);

        ex.submit(()->{
            try {
                tasks.getTask1Done();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });

        ex.submit(()->{
            try {
                tasks.getTask2Done();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });

        ex.submit(()->{
            try {
                tasks.getTask3Done();
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });
        ex.shutdown();

    }
}
