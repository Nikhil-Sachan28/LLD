package dev.nikhil.lldrepo.Module2.H__CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ex = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(3);// "I am waiting for 3 events."
        TaskNeededToBeCompleted tasks = new TaskNeededToBeCompleted(latch);

        ex.submit(()->{
            try {
                tasks.getTask1Done();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        ex.submit(()->{
            try {
                tasks.getTask2Done();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        ex.submit(()->{
            try {
                tasks.getTask3Done();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        ex.shutdown();

        latch.await();
        System.out.println("Main can execute Now");
    }
}
