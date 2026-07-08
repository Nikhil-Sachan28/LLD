package dev.nikhil.lldrepo.Module1.ThreadPool.delayQueue;

import java.util.concurrent.DelayQueue;

public class DelayQueueDemo {

    public static void main(String[] args) throws Exception {

        DelayQueue<DelayedTask> queue = new DelayQueue<>();

        queue.put(new DelayedTask("Task A", 5));
        queue.put(new DelayedTask("Task B", 2));
        queue.put(new DelayedTask("Task C", 8));

        /*
        Why doesn't ThreadPoolExecutor use DelayQueue by default?

        Because a normal thread pool is designed to execute tasks as soon as they're submitted.
        Delaying task execution is a different responsibility, which is why Java provides ScheduledThreadPoolExecutor
        (used behind ScheduledExecutorService) for scheduling tasks after a delay or at fixed intervals.
        **/

        while (!queue.isEmpty()) {

            DelayedTask task = queue.take();

            System.out.println(
                    System.currentTimeMillis()
                            + " -> "
                            + task.getName());
        }
    }
}