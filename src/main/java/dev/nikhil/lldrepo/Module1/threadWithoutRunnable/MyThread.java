package dev.nikhil.lldrepo.Module1.threadWithoutRunnable;

public class MyThread extends Thread{

    @Override
    public void run(){
        System.out.println("hello from new thread");
        System.out.println(Thread.currentThread().getName());
    }
}
