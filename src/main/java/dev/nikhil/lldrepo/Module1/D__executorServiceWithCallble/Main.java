package dev.nikhil.lldrepo.Module1.D__executorServiceWithCallble;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args){
        try{
            ExecutorService ex = Executors.newCachedThreadPool();
            Callable<Integer> runnable = new MyCallable();
            Future<Integer> fut = ex.submit(runnable);
            System.out.println(fut.get());
            ex.shutdown();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
