package dev.nikhil.lldrepo.Module1.ThreadPool.ArrayBlockingQueue;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println("hello from new thread" );

            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
