package dev.nikhil.lldrepo.Module1.ThreadPool.ArrayBlockingQueue;

import java.util.concurrent.*;

public class ArrayBlockingQueueDemo {
    public static void main(String[] args){
        try{

            ThreadPoolExecutor ex = new ThreadPoolExecutor(
                    2,
                    4,
                    10,
                    TimeUnit.SECONDS,
                    new java.util.concurrent.ArrayBlockingQueue<>(3)
            );
            System.out.println(ex.getActiveCount());
            Runnable runnable = new MyRunnable();
            ex.submit(runnable);
            ex.submit(runnable);
            ex.submit(runnable);
            ex.submit(runnable);
            ex.submit(runnable);
            ex.submit(runnable);
            System.out.println(ex.getActiveCount());
            // if capacity is 1, 4 threads are still used, as max 4 threads can be made
            // if capacity is 2, 4 threads used,
            // if capacity is 3, 3 threads
            // if capacity is 4, 2 threads
            // if capacity is 5, 2 threads, as min 2 threads are possible.
            ex.shutdown();
        }finally {

        }

    }
}
