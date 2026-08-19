package dev.nikhil.lldrepo.Module1.C__executorServiceWithRunnable;

import dev.nikhil.lldrepo.Module1.B__threadWithRunnable.MyRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args){
        ExecutorService ex = Executors.newFixedThreadPool(4);

        try{
            Runnable runnable = new MyRunnable();
            ex.execute(runnable);
        }finally {
            ex.shutdown();
        }




    }
}
