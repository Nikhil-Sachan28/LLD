package dev.nikhil.lldrepo.Module1_MultiThreading.B__threadWithRunnable;

public class Main {
    public static void main(String[] args){
        Runnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable, "thread-Nikhil");
//        Thread thread = new Thread(runnable);
        thread.start();
    }
}
