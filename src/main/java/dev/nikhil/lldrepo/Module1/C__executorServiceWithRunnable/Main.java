package dev.nikhil.lldrepo.Module1.C__executorServiceWithRunnable;

import dev.nikhil.lldrepo.Module1.A__threadWithRunnable.MyRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args){
        try{
            ExecutorService ex = Executors.newFixedThreadPool(4);
            Runnable runnable = new MyRunnable();
            ex.submit(runnable);
            ex.shutdown();
        }finally {

        }

    }
}
