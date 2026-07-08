package dev.nikhil.lldrepo.Module1.ThreadPool.PriorityBlockingQueue;

public class PriorityTask implements Runnable, Comparable<PriorityTask>{
    private final int priority;

    public PriorityTask(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(PriorityTask p2) {
        return Integer.compare(p2.priority, this.priority);
    }

    @Override
    public void run() {
        System.out.println(
                Thread.currentThread().getName()
                        + " Priority = "
                        + priority);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
