package dev.nikhil.lldrepo.Module1.F__scheduledExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecuteRepeatedly2 {
    public static void main(String[] args){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        executor.scheduleAtFixedRate(() -> {

            System.out.println("Start");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("End");

        },0,3,TimeUnit.SECONDS);

//        executor.shutdown();
    }
}
