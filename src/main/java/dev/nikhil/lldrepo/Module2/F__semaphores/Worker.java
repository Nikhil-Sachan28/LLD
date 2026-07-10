package dev.nikhil.lldrepo.Module2.F__semaphores;

import java.util.concurrent.Semaphore;

public class Worker implements Runnable{
    Semaphore semaphore;

    Worker(Semaphore semaphore){
        this.semaphore = semaphore;
    }


    @Override
    public void run() {
        try{
            semaphore.acquire();

            System.out.println(Thread.currentThread().getName()
                    + " entered");

            Thread.sleep(3000);

            System.out.println(Thread.currentThread().getName()
                    + " leaving");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }
}
