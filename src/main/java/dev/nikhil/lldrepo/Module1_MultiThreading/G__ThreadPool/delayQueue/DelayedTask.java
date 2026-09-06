package dev.nikhil.lldrepo.Module1_MultiThreading.G__ThreadPool.delayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Delayed, Runnable {

    private final String name;
    private final long endTime;

    public DelayedTask(String name, long delayInSeconds) {
        this.name = name;
        this.endTime = System.currentTimeMillis()
                + TimeUnit.SECONDS.toMillis(delayInSeconds);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long remaining = endTime - System.currentTimeMillis();
        return unit.convert(remaining, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed other) {
        return Long.compare(
                this.getDelay(TimeUnit.MILLISECONDS),
                other.getDelay(TimeUnit.MILLISECONDS)
        );
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println(name);
    }
}