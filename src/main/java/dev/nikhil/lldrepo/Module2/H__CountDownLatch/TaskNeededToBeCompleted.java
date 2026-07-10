package dev.nikhil.lldrepo.Module2.H__CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class TaskNeededToBeCompleted {
    CountDownLatch countDownLatch;

    TaskNeededToBeCompleted(CountDownLatch latch){
        countDownLatch = latch;
    }

    void getTask1Done() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("task1 completed");
        countDownLatch.countDown();// one events finishes
    }

    void getTask2Done() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("task2 completed");
        countDownLatch.countDown();// one events finishes
    }

    void getTask3Done() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("task3 completed");
        countDownLatch.countDown();// one events finishes
    }
}
