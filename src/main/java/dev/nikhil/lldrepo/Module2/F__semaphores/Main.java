package dev.nikhil.lldrepo.Module2.F__semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        Worker worker = new Worker(semaphore);
        ExecutorService ex = Executors.newFixedThreadPool(10);

        ex.submit(worker);
        ex.submit(worker);
        ex.submit(worker);
        ex.submit(worker);
        ex.submit(worker);
        ex.submit(worker);
        ex.submit(worker);
        ex.shutdown();
    }
}
