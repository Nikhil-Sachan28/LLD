package dev.nikhil.lldrepo.Module1.scheduledExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecuteAfterDelay {
    public static void main(String[] args){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        executor.schedule(() -> {
            System.out.println("Executed after 5 seconds");
        }, 5, TimeUnit.SECONDS);

        executor.shutdown();
    }
}
