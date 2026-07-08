package dev.nikhil.lldrepo.Module1.ThreadPool.PriorityBlockingQueue;

import java.util.concurrent.*;

public class PriorityBlockingQueueDemo {
    public static void main(String[] args){

        ThreadPoolExecutor ex = new ThreadPoolExecutor(
                1,
                4,
                10,
                TimeUnit.SECONDS,
                new PriorityBlockingQueue<>(3)
        );
        System.out.println(ex.getActiveCount());

        ex.execute(new PriorityTask(1));
        ex.execute(new PriorityTask(2));
        ex.execute(new PriorityTask(3));
        ex.execute(new PriorityTask(2));
        ex.execute(new PriorityTask(1));
        ex.execute(new PriorityTask(3));
        System.out.println(ex.getActiveCount());
        ex.shutdown();

    }
}
