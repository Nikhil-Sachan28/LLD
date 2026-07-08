package dev.nikhil.lldrepo.Module1.D__executorServiceWithCallble;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println("hello from new thread");
        System.out.println(Thread.currentThread().getName());
        return 1;
    }
}
