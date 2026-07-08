package dev.nikhil.lldrepo.Module1.A__threadWithRunnable;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("hello from new thread");
        System.out.println(Thread.currentThread().getName());
    }
}
