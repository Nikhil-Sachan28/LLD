package dev.nikhil.lldrepo.Module1.scheduledExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecuteRepeatedly {
    public static void main(String[] args){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        executor.scheduleAtFixedRate(
                () -> System.out.println("Running"),
                2,
                3,
                TimeUnit.SECONDS
        );

//        executor.shutdown();
    }
}
