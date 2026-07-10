package dev.nikhil.lldrepo.Module2.I__CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;

import java.util.concurrent.CyclicBarrier;

public class TaskNeededToBeCompleted {
    CyclicBarrier countDownLatch;

    TaskNeededToBeCompleted(CyclicBarrier latch){
        countDownLatch = latch;
    }

    void getTask1Done() throws BrokenBarrierException, InterruptedException {
        Thread.sleep(4000);
        System.out.println("paasenger1 available");
        countDownLatch.await();// one events finishes
        System.out.println("paasenger1 stepped on bus");
    }

    void getTask2Done() throws InterruptedException, BrokenBarrierException {
        Thread.sleep(5000);
        System.out.println("paasenger2 available");
        countDownLatch.await();// one events finishes
        System.out.println("paasenger2 stepped on bus");
    }

    void getTask3Done() throws InterruptedException, BrokenBarrierException {
        Thread.sleep(2000);
        System.out.println("paasenger3 available");
        countDownLatch.await();// one events finishes
        System.out.println("paasenger3 stepped on bus");
    }
}
